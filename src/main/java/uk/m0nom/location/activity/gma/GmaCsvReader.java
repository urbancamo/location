package uk.m0nom.location.activity.gma;

import org.apache.commons.csv.CSVRecord;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.CsvActivityReader;

/**
 * Expects a Global Mountain Activity Database Export file
 */
public class GmaCsvReader extends CsvActivityReader {

    public GmaCsvReader(String sourceFile) {
        super(Gma.TYPE, sourceFile);
    }

    @Override
    protected Activity readRecord(CSVRecord record) throws IllegalArgumentException {
        Gma info = new Gma();

        info.setRef(record.get("Reference"));
        info.setName(record.get("Name"));
        info.setAltitude(Double.parseDouble(record.get("Height (m)")));

        setCoords(info, readCoords(record,"Latitude", "Longitude"));
        info.setGrid(record.get("Maidenhead Locator"));

        return info;
    }
}
