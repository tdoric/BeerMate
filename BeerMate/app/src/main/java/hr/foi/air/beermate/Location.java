package hr.foi.air.beermate;

/**
 * Created by Tomislav on 12/21/2016.
 */

public class Location {

    float latitude,longtitude;
    String name;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location() {

    }

    public Location(float latitude, float longtitude, String name) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.name = name;
    }
}
