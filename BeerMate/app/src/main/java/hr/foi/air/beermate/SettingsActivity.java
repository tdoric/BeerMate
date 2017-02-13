package hr.foi.air.beermate;

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


    private String getIntentUserId() {
        String userId = "";
        if (getIntent() != null) {
            userId = getIntent().getStringExtra("userId");
        }
        return userId;
    }

    private String getIntentUserRating() {
        String userRating = "";
        if (getIntent() != null) {
            userRating = getIntent().getStringExtra("userRating");
        }
        return userRating;
    }

    private String getIntentUserName() {
        String userName = "";
        if (getIntent() != null) {
            userName = getIntent().getStringExtra("userName");
        }
        return userName;
    }

    public void updateStar(View view) {

        mReference = FirebaseDatabase.getInstance().getReference().child("users").child(getIntentUserId());
        mReference.child("userRating").setValue("1");
    }

    public void updateTap(View view){
        mReference = FirebaseDatabase.getInstance().getReference().child("users").child(getIntentUserId());
        mReference.child("userRating").setValue("2");
    }

    public void catchUsername(View view){

        editText.setEnabled(true);
        editText.setText(getIntentUserName());

    }

    public void updateUserName(View view) {
        if(editText.getText().toString().matches("")){
            Toast.makeText(SettingsActivity.this,"Empty username ",Toast.LENGTH_SHORT).show();
        }else {
            mReference = FirebaseDatabase.getInstance().getReference().child("users").child(getIntentUserId());
            mReference.child("userName").setValue(editText.getText().toString());
        }

    }
}
