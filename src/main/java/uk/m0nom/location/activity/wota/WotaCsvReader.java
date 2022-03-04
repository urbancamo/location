package uk.m0nom.location.activity.wota;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.ActivityDatabase;
import uk.m0nom.location.activity.CsvActivityReader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Expects a WOTA Summits Database Export file, reformatted as UTF-8 CSV with the following columns retained:
 *
 * wotaid
 * sotaid
 * book
 * name
 * height
 * reference
 * humpid
 * gridid
 */
public class WotaCsvReader extends CsvActivityReader {

    public WotaCsvReader(String sourceFile) {
        super(Wota.TYPE, sourceFile);
    }

    @Override
    public ActivityDatabase read(InputStream reader) throws IOException {
        return new WotaSummitsDatabase(Wota.TYPE, readRecords(reader));
    }

    @Override
    protected Activity readRecord(CSVRecord record) throws IllegalArgumentException {
        Wota info = new Wota();

        int wotaid = Integer.parseInt(record.get("wotaid"));
        if (wotaid <= 214) {
            info.setRef(String.format("LDW-%03d", wotaid));
        } else {
            info.setRef(String.format("LDO-%03d", wotaid - 214));
        }
        info.setInternalId(wotaid);

        String sotaId = record.get("sotaid");
        if (!StringUtils.equals(sotaId, "NULL")) {
            info.setSotaId(String.format("G/LD-%03d", Integer.parseInt(sotaId)));
        }

        info.setBook(record.get("book"));
        info.setName(record.get("name"));
        info.setAltitude(Double.valueOf(record.get("height")));
        info.setReference(record.get("reference"));

        String humpId = record.get("humpid");
        if (!StringUtils.equals(humpId, "NULL")) {
            info.setHemaId(String.format("G/HLD-%03d", Integer.parseInt(humpId)));
        }
        info.setGridId(record.get("gridid"));

        info.setX(Integer.parseInt(record.get("x")));
        info.setY(Integer.parseInt(record.get("y")));

        setCoords(info, readCoords(record, "lat", "long"));
        return info;
    }
}
