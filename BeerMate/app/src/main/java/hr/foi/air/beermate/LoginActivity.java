package hr.foi.air.beermate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private ProgressDialog progressDialog;
    private TextView  textViewRegister;
    private DatabaseReference mReference;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewRegister = (TextView) findViewById(R.id.textViewRegister);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
    }

    private void loginUser(){

        //getting email and password from edit texts
        final String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Login Please Wait...");
        progressDialog.show();

        //activity_login in app
       firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  progressDialog.dismiss();
                  Toast.makeText(LoginActivity.this,"Login successfully ",Toast.LENGTH_SHORT).show();
                  mReference = FirebaseDatabase.getInstance().getReference().child("users");
                  mReference.addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(DataSnapshot dataSnapshot) {
                          for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                              User user = messageSnapshot.getValue(User.class);
                              if(email.equals(user.getUserMail())){
                                  Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                  i.putExtra("userId",messageSnapshot.getKey());
                                  i.putExtra("userRating",user.getUserRating());
                                  Toast.makeText(LoginActivity.this,user.getUserRating(),Toast.LENGTH_LONG).show();
                                  startActivity(i);
                              }



                          }
                      }

                      @Override
                      public void onCancelled(DatabaseError databaseError) {

                      }
                  });


              }
               else{
                  progressDialog.dismiss();
                  Toast.makeText(LoginActivity.this,"Error with activity_login!",Toast.LENGTH_SHORT).show();
              }
           }
       });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonSignup:
                  loginUser();
                break;
            case R.id.textViewRegister:
                openRegister();
                break;

        }
    }

    private void openRegister() {
        Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
    }
}
