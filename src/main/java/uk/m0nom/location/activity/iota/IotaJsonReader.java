package uk.m0nom.location.activity.iota;

import com.fasterxml.jackson.databind.ObjectMapper;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.ActivityDatabase;
import uk.m0nom.location.activity.ActivityReader;
import uk.m0nom.location.coords.GlobalCoords3D;
import uk.m0nom.location.maidenheadlocator.MaidenheadLocatorConversion;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Reader for the Islands on the Air (IOTA) JSON export file
 */
public class IotaJsonReader extends ActivityReader {
    private static final Logger logger = Logger.getLogger(IotaJsonReader.class.getName());

    public IotaJsonReader(String sourceFile) {
        super(Iota.TYPE, sourceFile);
    }

    public ActivityDatabase read(InputStream inputStream) throws IOException {
        Map<String, Activity> iotaInfo = new HashMap<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            IotaResponse response = mapper.readValue(inputStream, IotaResponse.class);
            if ("ok".equals(response.getStatus())) {
                int i = 0;
                for (Iota info : response.getContent()) {
                    info.setType(Iota.TYPE);
                    info.setRef(info.getRefNo());
                    info.setName(info.getIotaName());
                    setCoords(info, info.getCoordsFromLatLongMaxMin());
                    info.setGrid(MaidenheadLocatorConversion.coordsToLocator(new GlobalCoords3D(info.getLatitude(), info.getLongitude())));
                    info.setIndex(i-1);
                    iotaInfo.put(info.getRefNo(), info);
                    i++;
                }
            }
        } catch (Exception ex) {
            logger.severe(String.format("Error reading IOTA JSON data: %s", ex.getMessage()));
        }

        return new ActivityDatabase(Iota.TYPE, Iota.class, iotaInfo);
    }
}
