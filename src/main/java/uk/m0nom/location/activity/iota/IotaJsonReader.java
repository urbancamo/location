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

    public ActivityDatabase read(String activityType, InputStream inputStream) throws IOException {
        Map<String, Activity> iotas = new HashMap<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            IotaResponse response = mapper.readValue(inputStream, IotaResponse.class);
            if ("ok".equals(response.getStatus())) {
                int i = 0;
                for (IotaInfo info : response.getContent()) {
                    Iota iota = new Iota();
                    iota.setRef(info.getRefNo());
                    iota.setName(info.getIotaName());
                    GlobalCoords3D coords3D = info.getCoordsFromLatLongMaxMin();
                    iota.setLatitude(coords3D.getLatitude());
                    iota.setLongitude(coords3D.getLongitude());
                    iota.setGrid(MaidenheadLocatorConversion.
                            coordsToLocator(new GlobalCoords3D(iota.getLatitude(), iota.getLongitude())));

                    iota.setLatitudeMax(info.getLatitudeMax());
                    iota.setLongitudeMax(info.getLongitudeMax());
                    iota.setGroupRegion(info.getGroupRegion());
                    iota.setWhitelist(info.getWhitelist());
                    iota.setPcCredited(info.getPcCredited());
                    iota.setComment(info.getComment());
                    iota.setSubGroups(info.getSubGroups());

                    iotas.put(iota.getRef(), iota);
                    i++;
                }
            }
        } catch (Exception ex) {
            logger.severe(String.format("Error reading IOTA JSON data: %s", ex.getMessage()));
        }

        return new ActivityDatabase(Iota.TYPE, Iota.class, iotas);
    }
}
