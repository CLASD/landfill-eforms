package sanitation.la.project.myapplication.data;

import java.util.Date;

/**
 * Created by saul on 2/13/16.
 */
public class LocationPoint {

    private double lat, lon;
    private Date when;

    public LocationPoint(double la, double lo, Date now){
        lat = la;
        lon = lo;
        when = now;

    }

    public String toString(){
        return String.format("Lat: %.8f  Long: %.8f   Time: %s", lat, lon, when.toString());
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }


}
