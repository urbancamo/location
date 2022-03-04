package uk.m0nom.location.activity;

import java.util.Collection;
import java.util.Map;

/**
 * This class groups all locations for an activity in a Map that can be searched using the primary reference
 * It also contains a method to obtain all activities within a given radius of a location
 */
public class ActivityDatabase {
    private final String type;
    private final Class activityClass;

    private final Map<String, Activity> database;
    private final boolean specialEventActivity;

    public ActivityDatabase(String type, Class activityClass, Map<String, Activity> database) {
        this.type = type;
        this.activityClass = activityClass;
        this.database = database;
        this.specialEventActivity = false;
    }

    public Class getActivityClass() { return  activityClass; }

    public String getType() {
        return type;
    }

    public Activity get(String ref) {
        return database.get(ref);
    }

    public boolean isSpecialEventActivity() {
        return specialEventActivity;
    }

    public Collection<Activity> getValues() {
        return database.values();
    }

    public int size() { return database.size(); }
}
