package hr.foi.air.beermate;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import hr.foi.air.beermate.MapsActivity;

/**
 * Activity  za prikaz glavnog izbornika
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Metoda koja vrsi dodavanje parametara u intent tako da bi aktivnost koju  poziva
     * moze primiti parametre te na osnovu njih iscitati korisnikove podatke
     * @param view
     */

    public void openBeers(View view){
        Intent intent = new Intent(this,BeersListActivity.class);
        intent.putExtra("userId", getIntentUserId());
        intent.putExtra("userRating", getIntentUserRating());
        intent.putExtra("userName", getIntentUserName());
        startActivity(intent);

    }

    /**
     * Metoda za pokretanje aktivnosti mape
     * @param view
     */
    public void openMapsActivity (View view){
       Intent i = new Intent(this,MapsActivity.class);
       startActivity(i);
    }

    /**
     * Metoda koja vrsi dodavanje parametara u intent tako da bi aktivnost koju  poziva
     * moze primiti parametre te na osnovu njih iscitati korisnikove podatke
     * @param view
     */

    public void openSettings (View view){
        Intent i = new Intent(this,SettingsActivity.class);
        i.putExtra("userId", getIntentUserId());
        i.putExtra("userRating", getIntentUserRating());
        i.putExtra("userName", getIntentUserName());
        startActivity(i);
    }

    /**
     * Metoda za zatvaranje glavne aktivnosti
     * @param view
     */

    public void logOut(View view){

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    /**
     * Dohvacanje intenta kako bismo znali koji je user logiran u aplikaciju odnosno idUsera
     * @return
     */

    private String getIntentUserId() {
        String userId = "";
        if (getIntent() != null) {
            userId = getIntent().getStringExtra("userId");
        }
        return userId;
    }
    /**
     * Dohvacanje intenta kako bismo znali koje je nacin ocjenivanja logiranog korisnika
     * @return
     */

    private String getIntentUserRating() {
        String userRating = "";
        if (getIntent() != null) {
            userRating = getIntent().getStringExtra("userRating");
        }
        return userRating;
    }

    /**
     * Dohvacanje intenta kako bismo znali koji je userName logiranog korisnika
     * @return
     */

    private String getIntentUserName() {
        String userName = "";
        if (getIntent() != null) {
            userName = getIntent().getStringExtra("userName");
        }
        return userName;
    }
}
