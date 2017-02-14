package hr.foi.air.beermate;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity za prikaz google maps-a
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference mReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Metoda koja kreira google mapu te na njoj postavlja markere s podacima iz baze podataka
     * hardkodiran je grad Varazdin te se zumira grad Varazdin
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mReference = FirebaseDatabase.getInstance().getReference().child("locations");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    Location singleLocation = messageSnapshot.getValue(Location.class);
                    LatLng pubs = new LatLng(singleLocation.getLatitude(),singleLocation.getLongtitude());
                    mMap.addMarker(new MarkerOptions().position(pubs).title(singleLocation.getName()).snippet(singleLocation.getDescription()));
                    mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(46.305746,16.336607) , 14.0f) );

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
