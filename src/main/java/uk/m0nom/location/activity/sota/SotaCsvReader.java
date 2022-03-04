package uk.m0nom.location.activity.sota;

import org.apache.commons.csv.CSVRecord;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.CsvActivityReader;

/**
 * Expects a SOTA Summits Database Export file, reformatted as UTF-8 CSV with the following columns retained:
 *
 * SummitCode
 * AltM
 * Longitude
 * Latitude
 * Points
 * BonusPoints
 */
public class SotaCsvReader extends CsvActivityReader {

    public SotaCsvReader(String sourceFile) {
        super(Sota.TYPE, sourceFile);
    }

    @Override
    protected Activity readRecord(CSVRecord record) throws IllegalArgumentException {
        Sota info = new Sota();

        info.setRef(record.get("SummitCode"));
        info.setName(record.get("SummitName"));
        info.setAltitude(Double.parseDouble(record.get("AltM")));

        setCoords(info, readCoords(record,"Latitude", "Longitude"));
        info.setPoints(Integer.parseInt(record.get("Points")));
        info.setBonusPoints(Integer.parseInt(record.get("BonusPoints")));
        return info;
    }
}
