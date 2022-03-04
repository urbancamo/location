package uk.m0nom.location.activity.wwff;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.CsvActivityReader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.IllformedLocaleException;

/**
 * Reader for the Worldwide Flora Fauna CSV extract file
 */
public class WwffCsvReader extends CsvActivityReader {

    public WwffCsvReader(String sourceFile) {
        super(Wwff.TYPE, sourceFile);
    }

    @Override
    protected Activity readRecord(CSVRecord record) throws IllegalArgumentException {
        Wwff info = new Wwff();

        info.setRef(record.get("reference"));
        info.setName(record.get("name"));
        info.setActive(StringUtils.equals(record.get("status"), "active"));
        setCoords(info, readCoords(record, "latitude", "longitude"));

        info.setProgram(record.get("program"));
        info.setDxcc(record.get("dxcc"));
        info.setState(record.get("state"));
        info.setCounty(record.get("county"));
        info.setContinent(record.get("continent"));
        info.setIota(record.get("iota"));
        info.setIaruLocator(record.get("iaruLocator"));
        info.setIUCNcat(record.get("IUCNcat"));

        info.setValidFrom(record.get("validFrom"));
        info.setValidTo(record.get("validTo"));
        info.setNotes(record.get("notes"));
        info.setLastMod(record.get("lastMod"));
        info.setChangeLog(record.get("changeLog"));
        info.setReviewFlag(record.get("reviewFlag"));

        info.setSpecialFlags(record.get("specialFlags"));
        info.setWebsite(record.get("website"));
        info.setCountry(record.get("country"));
        info.setRegion(record.get("region"));
        return info;
    }
}
