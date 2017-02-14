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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Activity za potrebe registracije korisnika
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister,btnBack;
    EditText editTextUsername,editTextMail,editTextPasswordRegister,editTextRepeatPassword;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mReference;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername=(EditText)findViewById(R.id.editTextUsername);
        editTextMail=(EditText)findViewById(R.id.editTextMailRegister);
        editTextPasswordRegister=(EditText)findViewById(R.id.editTextPasswordRegister);
        editTextRepeatPassword=(EditText)findViewById(R.id.editTextRepeatPassword);
        btnRegister= (Button) findViewById(R.id.buttonRegister);
        btnBack = (Button) findViewById(R.id.buttonBack);

        btnRegister.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    /**
     * Metoda koja prima parametre
     * @param insertMail
     * @param insertName
     * @param language
     * @param rating
     *
     * te na osnovu tih paramatera vrsi insert novog korisnika u bazu podataka
     */
    private void insertData(String insertMail, String insertName, String language, String rating ) {
        mReference = FirebaseDatabase.getInstance().getReference().child("users");
        User user = new User();
        user.setUserName(insertName);
        user.setUserLanguage(language);
        user.setUserRating(rating);
        user.setUserMail(insertMail);
        mReference.push().setValue(user);

    }

    /**
     * Metoda koja provjerava da li je korisnik popunio sva polja odnosno editTextove te pomoću firebase
     * UI vrsi proces kreiranja novog usera u firebase authentication
     */
    private void registerUser(){

        final String mail = editTextMail.getText().toString();
        String password  = editTextPasswordRegister.getText().toString();
        String repeatP = editTextRepeatPassword.getText().toString();
        final String nameU = editTextUsername.getText().toString();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(nameU)){
            Toast.makeText(this,"Please enter username",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(mail)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        if(!TextUtils.equals(password,repeatP)){
            Toast.makeText(this,"Passwords not match",Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this,"Registering successfully ",Toast.LENGTH_SHORT).show();
                    insertData(mail,nameU,"1","1");
                    Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(i);
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this,"Error with registering!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * Listener koji sluzi da izvrsi metode kada se klikne na odredeni gumb
     * @param v
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonRegister:
                registerUser();
                break;
            case R.id.buttonBack:
                backToLogin();
                break;
        }
    }

    /**
     * Metoda za povratak na login ukoliko korisnik odustane od registracije
     */

    private void backToLogin() {
        Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(i);
    }
}
