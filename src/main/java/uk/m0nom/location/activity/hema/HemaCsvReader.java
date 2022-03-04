package uk.m0nom.location.activity.hema;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.CsvActivityReader;

/**
 * HuMPS on the Air CSV reader - the export having been provided by Rob.
 */
public class HemaCsvReader extends CsvActivityReader {

    public HemaCsvReader(String sourceFile) {
        super(Hema.TYPE, sourceFile);
    }

    @Override
    protected Activity readRecord(CSVRecord record) throws IllegalArgumentException {
        Hema info = new Hema();
        info.setKey(Integer.parseInt(record.get("hHillKey")));

        info.setRef(record.get("hFullReference"));
        info.setAltitude(Double.parseDouble(record.get("hHeightM")));

        setCoords(info, readCoords(record, "hLatitude", "hLongitude"));

        info.setActive(StringUtils.equals(record.get("hActive"), "Y"));
        info.setName(record.get("hName"));

        return info;
    }
}
