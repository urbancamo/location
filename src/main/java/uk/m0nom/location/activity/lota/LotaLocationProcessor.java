package uk.m0nom.location.activity.lota;

import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.ActivityDatabase;
import uk.m0nom.location.activity.ActivityReader;
import uk.m0nom.location.coords.LocationParserResult;
import uk.m0nom.location.coords.LocationParsers;
import uk.m0nom.location.coords.LocationSource;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * Used to standardize the various location formats used in the master Lighthouses extract file
 */
public class LotaLocationProcessor {
    private static final Logger logger = Logger.getLogger(LotaLocationProcessor.class.getName());

    private static LocationParsers locationParsers = null;

    private static int extractLocationInformation(ActivityDatabase lotaDb) {
        Collection<Activity> values = lotaDb.getValues();
        int count = 0;
        for (Activity activity : values) {
            Lota Lota = (uk.m0nom.location.activity.lota.Lota) activity;
            if (extractLocationInformation(Lota)) {
                count++;
            }
        }
        return count;
    }

    private static boolean extractLocationInformation(Lota lota) {
        // The location information is stored in all sorts of ways, so we have to go through each one
        // Start with the most accurate attempting to parse Latitude/Longitude in all the variants
        LocationParserResult result = locationParsers.parseStringForCoordinates(LocationSource.ACTIVITY, lota.getLocation());
        if (result != null) {
            ActivityReader.setCoords(lota, result.getCoords());
            //lota.setAltitude(coords.getAltitude());
            return true;
        }
        return false;
    }

}
