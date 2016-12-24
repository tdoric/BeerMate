package hr.foi.air.beermate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import hr.foi.air.beermate.MapsActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openBeers(View view){
        Intent intent = new Intent(this,BeersListActivity.class);
        startActivity(intent);
    }

    public void openMapsActivity (View view){
       Intent i = new Intent(this,MapsActivity.class);
       startActivity(i);
    }

    public void logOut(View view){

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
