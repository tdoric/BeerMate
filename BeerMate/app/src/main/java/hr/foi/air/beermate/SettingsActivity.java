package hr.foi.air.beermate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Set;

/**
 * Activity koji sadrzi potrebne metode za obavljanje funkcionalnosti
 */

public class SettingsActivity extends AppCompatActivity {

    private DatabaseReference mReference;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        editText = (EditText) this.findViewById(R.id.editTextUsername);
        editText.setEnabled(false);
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

    /**
     * Metoda koja prima view te na osnovu eventa aktivira metodu koja azurira stanje u bazi podataka
     * @param view
     */

    public void updateStar(View view) {

        mReference = FirebaseDatabase.getInstance().getReference().child("users").child(getIntentUserId());
        mReference.child("userRating").setValue("1");
    }

    /**
     * Metoda koja prima view te na osnovu eventa aktivira metodu koja azurira stanje u bazi podataka
     * @param view
     */

    public void updateTap(View view){
        mReference = FirebaseDatabase.getInstance().getReference().child("users").child(getIntentUserId());
        mReference.child("userRating").setValue("2");
    }

    /**
     * Metoda koja prima view te na osnovu eventa dohvaca username korisnika
     * @param view
     */

    public void catchUsername(View view){

        editText.setEnabled(true);
        editText.setText(getIntentUserName());

    }

    /**
     * Metoda prima view te na osnovu eventa azurira stanje u bazi podataka i provjerava
     * da li je korisnik unio vrijednost u editText
     * @param view
     */

    public void updateUserName(View view) {
        if(editText.getText().toString().matches("")){
            Toast.makeText(SettingsActivity.this,"Empty username ",Toast.LENGTH_SHORT).show();
        }else {
            mReference = FirebaseDatabase.getInstance().getReference().child("users").child(getIntentUserId());
            mReference.child("userName").setValue(editText.getText().toString());
            editText.setEnabled(false);
        }

    }

    /**
     * Metoda koja prima view te na osnovu eventa zatvara settings i postavlja intent kako bi ga mogla koristiti druga aktivnost
     * @param view
     */

    public void closeSettings(View view) {
        Intent i = new Intent(SettingsActivity.this,MainActivity.class);
        i.putExtra("userId", getIntentUserId());
        i.putExtra("userRating", getIntentUserRating());
        i.putExtra("userName", getIntentUserName());
        startActivity(i);

    }
}
