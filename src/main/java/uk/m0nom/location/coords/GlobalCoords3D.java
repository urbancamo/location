package uk.m0nom.location.coords;

import lombok.Getter;
import lombok.Setter;
import org.gavaghan.geodesy.GlobalCoordinates;

@Getter
@Setter
public class GlobalCoords3D extends GlobalCoordinates {
    private LocationInfo locationInfo;
    private Double altitude;

    public GlobalCoords3D(double latitude, double longitude) {
        super(latitude, longitude);
        setLocationInfo(new LocationInfo(LocationAccuracy.LAT_LONG));
    }

    public GlobalCoords3D(GlobalCoordinates coordinates, LocationSource source, LocationAccuracy accuracy) {
        super(coordinates.getLatitude(), coordinates.getLongitude());
        setLocationInfo(source, accuracy);
    }

    public GlobalCoords3D(GlobalCoordinates coordinates, Double altitude) {
        super(coordinates.getLatitude(), coordinates.getLongitude());
        setAltitude(altitude);
        setLocationInfo(LocationSource.UNDEFINED, LocationAccuracy.LAT_LONG);
    }

    public GlobalCoords3D(double latitude, double longitude, Double altitude) {
        super(latitude, longitude);
        setAltitude(altitude);
        setLocationInfo(new LocationInfo(LocationAccuracy.LAT_LONG));
    }

    public GlobalCoords3D(double latitude, double longitude, Double altitude, LocationInfo locationInfo) {
        super(latitude, longitude);
        setAltitude(altitude);
        setLocationInfo(locationInfo);
    }

    public GlobalCoords3D(double latitude, double longitude, LocationSource source, LocationAccuracy accuracy) {
        super(latitude, longitude);
        setLocationInfo(source, accuracy);
    }

    public GlobalCoords3D(double latitude, double longitude, Double altitude, LocationSource source, LocationAccuracy accuracy) {
        super(latitude, longitude);
        setAltitude(altitude);
        setLocationInfo(source, accuracy);
    }

    public GlobalCoords3D(GlobalCoordinates coordinates, Double altitude, LocationInfo locationInfo) {
        super(coordinates.getLatitude(), coordinates.getLongitude());
        setAltitude(altitude);
        setLocationInfo(locationInfo);
    }

    public void setLocationInfo(LocationSource source, LocationAccuracy accuracy) {
        setLocationInfo(new LocationInfo(accuracy, source));
    }
}
