package uk.m0nom.location.activity.pota;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.CsvActivityReader;

/**
 * Reader for the Parks on the Air CSV extract
 */
public class PotaCsvReader extends CsvActivityReader {

    public PotaCsvReader(String sourceFile) {
        super(Pota.TYPE, sourceFile);
    }

    @Override
    protected Activity readRecord(CSVRecord record) throws IllegalArgumentException {
        Pota info = new Pota();
        info.setRef(record.get("reference"));
        info.setName(record.get("name"));
        info.setActive(StringUtils.equals(record.get("active"), "1"));
        String entityId = record.get("entityId");
        if (StringUtils.isNotBlank(entityId)) {
            info.setEntityId(Integer.parseInt(entityId));
        }
        info.setLocationDesc(record.get("locationDesc"));

        setCoords(info, readCoords(record, "latitude", "longitude"));
        info.setGrid(record.get("grid"));
        return info;
    }
}
