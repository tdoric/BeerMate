package hr.foi.air.beermate;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Tomislav on 2/12/2017.
 */

public class BeerAvailability extends RecyclerView.ViewHolder {

    View mView;
    Dialog showDialog;
    private DatabaseReference mReference;
    ArrayAdapter arrayAdapter;


    public BeerAvailability(View itemView) {
        super(itemView);
        mView=itemView;
    }


    public  void showAvailability(String id){

        showDialog = new Dialog(mView.getContext(), R.style.FullHeightDialog);
        showDialog.setContentView(R.layout.availability_dialog);
        showDialog.setCancelable(true);
        mReference = FirebaseDatabase.getInstance().getReference().child("beers").child(id).child("availability");
        final ListView listView = (ListView) showDialog.findViewById(R.id.pub_show_list);
        final ArrayList<String> names = new ArrayList<String>();
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    LocationBeer locationBeer = messageSnapshot.getValue(LocationBeer.class);
                    Toast.makeText(mView.getContext(), locationBeer.getName(), Toast.LENGTH_LONG).show();
                    names.add(locationBeer.getName());

                }
                arrayAdapter = new ArrayAdapter(mView.getContext(),android.R.layout.simple_list_item_1,names);
                listView.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        Button cancelBtn = (Button) showDialog.findViewById(R.id.pub_show_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog.dismiss();
            }
        });
        showDialog.show();
    }

}
