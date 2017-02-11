package hr.foi.air.beermate;

/**
 * Created by Tomislav on 2/11/2017.
 */

public class User {

    String userLanguage;
    String userRating;
    String userName;
    String userMail;

    public User() {
    }

    public User(String userLanguage, String userRating, String userName, String userMail) {
        this.userLanguage = userLanguage;
        this.userRating = userRating;
        this.userName = userName;
        this.userMail = userMail;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
}
