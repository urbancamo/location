package uk.m0nom.location.activity;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import uk.m0nom.location.activity.sota.Sota;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public abstract class CsvActivityReader extends ActivityReader {
    private static final Logger logger = Logger.getLogger(CsvActivityReader.class.getName());

    public CsvActivityReader(String type,  String sourceFile) {
        super(type, sourceFile);
    }

    public Map<String, Activity> readRecords(InputStream inputStream) throws IOException {
        Map<String, Activity> activityMap = new HashMap<>();

        final Reader reader = new InputStreamReader(new BOMInputStream(inputStream), StandardCharsets.UTF_8);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
        int line = 0;
        for (CSVRecord record : records) {
            line++;
            try {
                Activity info = readRecord(record);
                activityMap.put(info.getRef(), info);
            } catch (IllegalArgumentException e) {
                logger.severe(String.format("Error reading line %d: %s", line, e.getMessage()));
            }
        }
        return activityMap;
    }

    public ActivityDatabase read(String activityType, InputStream reader) throws IOException {
        return new ActivityDatabase(activityType, Sota.class, readRecords(reader));
    }

    protected abstract Activity readRecord(CSVRecord record) throws IllegalArgumentException;

}
