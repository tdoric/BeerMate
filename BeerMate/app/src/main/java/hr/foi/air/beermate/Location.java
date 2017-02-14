package hr.foi.air.beermate;

/**
 * Created by Tomislav on 12/21/2016.
 */

/**
 * Model koji služi za prikazivanje lokacija na google maps-u
 */
public class Location {

    float latitude,longtitude;
    String name,description;

    /**
     * Prazan konstruktor potreban za dohvaćanje s Firebase
     */
    public Location() {
    }

    /**
     * Konstruktor koji prima parametre
     * @param latitude
     * @param longtitude
     * @param name
     * @param description
     */
    public Location(float latitude, float longtitude, String name, String description) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.name = name;
        this.description = description;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
