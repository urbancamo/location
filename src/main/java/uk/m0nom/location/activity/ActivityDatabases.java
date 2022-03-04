package uk.m0nom.location.activity;

import lombok.Getter;
import lombok.Setter;
import uk.m0nom.location.activity.cota.Cota;
import uk.m0nom.location.activity.cota.CotaCsvReader;
import uk.m0nom.location.activity.gma.Gma;
import uk.m0nom.location.activity.gma.GmaCsvReader;
import uk.m0nom.location.activity.hema.Hema;
import uk.m0nom.location.activity.hema.HemaCsvReader;
import uk.m0nom.location.activity.iota.Iota;
import uk.m0nom.location.activity.iota.IotaJsonReader;
import uk.m0nom.location.activity.lota.Lota;
import uk.m0nom.location.activity.lota.LotaCsvReader;
import uk.m0nom.location.activity.pota.Pota;
import uk.m0nom.location.activity.pota.PotaCsvReader;
import uk.m0nom.location.activity.rota.Rota;
import uk.m0nom.location.activity.rota.RotaCsvReader;
import uk.m0nom.location.activity.sota.Sota;
import uk.m0nom.location.activity.sota.SotaCsvReader;
import uk.m0nom.location.activity.wota.Wota;
import uk.m0nom.location.activity.wota.WotaCsvReader;
import uk.m0nom.location.activity.wwff.Wwff;
import uk.m0nom.location.activity.wwff.WwffCsvReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This is the collection of all activity databases, including the readers to obtain the data from the source
 * files
 */
@Getter
@Setter
public class ActivityDatabases {
    private static final Logger logger = Logger.getLogger(ActivityDatabases.class.getName());

    private Map<String, ActivityDatabase> databases;
    private Map<String, ActivityReader> readers;

    /**
     * Constructor creates readers for all supported activities
     */
    public ActivityDatabases() {
        databases = new HashMap<>();
        readers = new HashMap<>();

        readers.put(Hema.TYPE, new HemaCsvReader("hema/HEMA-summits.csv"));
        readers.put(Sota.TYPE, new SotaCsvReader( "sota/summitslist.csv"));
        readers.put(Gma.TYPE, new GmaCsvReader( "gma/gma_summits.csv"));
        readers.put(Pota.TYPE, new PotaCsvReader("pota/all_parks_ext.csv"));
        readers.put(Wota.TYPE, new WotaCsvReader( "wota/summits.csv"));
        readers.put(Wwff.TYPE, new WwffCsvReader("wwff/wwff_directory.csv"));
        readers.put(Cota.TYPE, new CotaCsvReader("cota/cota.csv"));
        readers.put(Lota.TYPE, new LotaCsvReader("lota/lighthouses.csv"));
        readers.put(Rota.TYPE, new RotaCsvReader("rota/2021-rota.csv"));
        //readers.put(Iota.TYPE, new IotaJsonReader("iota/iota-full-list.json"));
    }

    /**
     * For each supported activity this calls the reader to load the activity data into a database and
     * then maintain a reference here using the ActivityType. Source files are read from the resources directory
     */
    public void loadData() {
        for (ActivityReader reader : readers.values()) {
            try {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(reader.getSourceFile());
                if (inputStream == null) {
                    logger.severe(String.format("Can't load %s using classloader %s", reader.getSourceFile(), getClass().getClassLoader().toString()));
                }
                //logger.info(String.format("Loading %s data from: %s", reader.getType().getActivityDescription(), reader.getSourceFile()));
                ActivityDatabase database = reader.read(inputStream);
                databases.put(reader.getType(), database);
                //logger.info(String.format("%d %s records loaded", database.size(), reader.getType().getActivityDescription()));
            } catch (IOException e) {
                logger.severe(String.format("Exception thrown reading activity databases: %s", e.getMessage()));
            }
        }
    }

    public ActivityDatabase getDatabase(String type) {
        return databases.get(type);
    }

    /**
     * Given an arbitrary activity reference, search each of the databases for a matching activity
     * @param reference String reference for the activity to search for
     * @return If a match is found the corresponding activity is returned, otherwise null
     */
    public Activity findActivity(String reference) {
        for (String activityType : databases.keySet()) {
            ActivityDatabase database = getDatabase(activityType);
            Activity activity = database.get(reference);
            if (activity != null) {
                return activity;
            }
        }
        return null;
    }
}
