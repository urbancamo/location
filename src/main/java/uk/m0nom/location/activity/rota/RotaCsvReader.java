package uk.m0nom.location.activity.rota;

import org.apache.commons.csv.CSVRecord;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.CsvActivityReader;

/**
 * Railways on the Air CSV reader. I created the CSV file from the https://rota.barac.org.uk/stations stations
 * list for 2021. It had a couple of additional entries following the 2021 contest based on the stations we contacted
 * as GB4LHR.
 */
public class RotaCsvReader extends CsvActivityReader {

    public RotaCsvReader(String sourceFile) {
        super(Rota.TYPE, sourceFile);
    }

    @Override
    protected Activity readRecord(CSVRecord record) throws IllegalArgumentException {
        Rota info = new Rota();
        info.setRef(record.get("Callsign"));
        info.setName(record.get("Railway"));
        info.setClub(record.get("Club"));
        info.setWab(record.get("WAB"));
        info.setGrid(record.get("Grid"));
        setCoords(info, readCoords(record, "Latitude", "Longitude"));
        return info;
    }
}
