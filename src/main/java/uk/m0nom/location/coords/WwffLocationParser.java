package uk.m0nom.location.coords;

import lombok.Getter;
import lombok.Setter;
import uk.m0nom.location.activity.ActivityDatabase;
import uk.m0nom.location.activity.wwff.Wwff;

import java.util.regex.Pattern;

@Getter
@Setter
public class WwffLocationParser implements LocationParser {
    private ActivityDatabase wwffDatabase;
    private final static Pattern PATTERN = Pattern.compile("([\\w\\d]+FF-\\d\\d\\d\\d)");

    public WwffLocationParser(ActivityDatabase wwffDatabase) {
        setWwffDatabase(wwffDatabase);
    }

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public GlobalCoords3D parse(LocationSource source, String location) {
        Wwff wwff = (Wwff) wwffDatabase.get(location);
        if (wwff != null) {
            if (wwff.getLatitude() != null) {
                return new GlobalCoords3D(wwff.getLatitude(), wwff.getLongitude(), LocationSource.ACTIVITY, LocationAccuracy.LAT_LONG);
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "WWFF Location";
    }
}
