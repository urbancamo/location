package uk.m0nom.location.activity.lota;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.csv.CSVRecord;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.CsvActivityReader;

import java.util.logging.Logger;

/**
 * CSV Reader for the Lighthouses and Lightships on the Air Programme data
 */
@Getter
@Setter
public class LotaCsvReader extends CsvActivityReader {
    private static final Logger logger = Logger.getLogger(LotaCsvReader.class.getName());

    public LotaCsvReader(String sourceFile) {
        super(Lota.TYPE, sourceFile);
     }

    @Override
    protected Activity readRecord(CSVRecord record) throws IllegalArgumentException {
        Lota info = new Lota();
        info.setCountry(record.get("Country").trim());
        info.setName(record.get("Lighthouse Name").trim());
        info.setStatus(record.get("Status").trim());

        if (info.getName().contains("Deleted.")) {
            info.setStatus("D");
            info.setName(info.getName().replace("Deleted.", ""));
        }
        info.setDxcc(record.get("DXCC").trim());
        info.setContinent(record.get("Continent").trim());
        info.setLocation(record.get("Location").trim());
        info.setRef(record.get("ILLW").trim());
        setCoords(info, readCoords(record, "Latitude", "Longitude"));
        return info;
    }
}
