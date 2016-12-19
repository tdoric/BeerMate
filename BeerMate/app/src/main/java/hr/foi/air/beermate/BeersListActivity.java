package hr.foi.air.beermate;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class BeersListActivity extends AppCompatActivity {
    private RecyclerView beersList;
    private DatabaseReference mReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        mReference = FirebaseDatabase.getInstance().getReference().child("beers");

        beersList = (RecyclerView) findViewById(R.id.main_recycler);
        beersList.setHasFixedSize(true);
        beersList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Beer,BeerViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Beer, BeerViewHolder>(
                Beer.class,R.layout.beer_row,BeerViewHolder.class,mReference
        ) {
            @Override
            protected void populateViewHolder(BeerViewHolder viewHolder, Beer model, int position) {
                viewHolder.setTitle(model.getName());
                viewHolder.setDesc(model.getStrength());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                viewHolder.setCountry(model.getCountry());
                viewHolder.setBrewery(model.getBrewery());
                viewHolder.setPercentage(model.getPercentage());
            }
        };

        beersList.setAdapter(firebaseRecyclerAdapter);
    }



}
