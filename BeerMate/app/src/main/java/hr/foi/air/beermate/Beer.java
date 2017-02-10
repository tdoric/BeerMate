package hr.foi.air.beermate;

/**
 * Created by Tomislav on 11/4/2016.
 */

public class Beer {

    String uid;
    String name;
    String strength;
    String image;
    String country;
    String brewery;
    Float percentage;
    int totalVotes;
    int numberOfVotes;

    public Beer(String uid,String name, String strength, String image, String country, String brewery, Float percentage,int totalVotes,int numberOfVotes) {
        this.uid= uid;
        this.name = name;
        this.strength = strength;
        this.image = image;
        this.country = country;
        this.brewery = brewery;
        this.percentage = percentage;
        this.totalVotes=totalVotes;
        this.numberOfVotes=numberOfVotes;
    }

    public Beer() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }
}
