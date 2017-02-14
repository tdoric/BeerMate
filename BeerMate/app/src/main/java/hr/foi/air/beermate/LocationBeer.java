package hr.foi.air.beermate;

/**
 * Created by Tomislav on 2/12/2017.
 */

/**
 * Model za prikazivanje dostupnosti
 */

public class LocationBeer {

     String name;

    /**
     * Prazan konstruktor potreban za dohvaćanje s Firebase
     */

    public LocationBeer() {
    }

    /**
     * Konstruktor koji prima parametar
     * @param name
     */
    public LocationBeer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
