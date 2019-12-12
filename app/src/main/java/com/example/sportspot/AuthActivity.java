package com.example.sportspot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportspot.util.Const;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
    private EditText auth_username;
    private EditText auth_password;
    private Button auth_connect;
    private TextView auth_signup;
    private Intent intent;
    private FirebaseAuth mAuth;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initComponents();

        mAuth = FirebaseAuth.getInstance();

        auth_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AuthActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        auth_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectUser();
            }
        });

        if(getUserEmailFromSP() != null){
            Intent intentSP = new Intent(AuthActivity.this, SportsListActivity.class);
            startActivity(intentSP);
        }
    }

    private void initComponents(){
        auth_username = findViewById(R.id.auth_username);
        auth_password = findViewById(R.id.auth_password);
        auth_connect = findViewById(R.id.auth_connect_button);
        auth_signup = findViewById(R.id.auth_signup_button);
    }

    public void saveUserAuthCredentialsInSP(String email, String password){
        sp = getSharedPreferences(Const.SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Const.SP_EMAIL_KEY, email);
        editor.putString(Const.SP_PASSWORD_KEY, password);
        editor.apply();
    }

    public String getUserEmailFromSP(){
        sp = getSharedPreferences(Const.SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(Const.SP_EMAIL_KEY, null);

    }

    public String getUserPasswordFromSP(){
        sp = getSharedPreferences(Const.SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(Const.SP_PASSWORD_KEY, null);


    }

    private void connectUser(){
        final String textMail = auth_username.getText().toString().trim();
        final String textParola = auth_password.getText().toString().trim();

        if(textMail.isEmpty()){
            auth_username.setError("Introduceti o adresa email.");
            auth_username.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(textMail).matches()){
            auth_username.setError("Introduceti o adresa email valida.");
            auth_username.requestFocus();
            return;
        }

        if(textParola.isEmpty()){
            auth_password.setError("Introduceti o parola");
            auth_password.requestFocus();
            return;
        }

        if(textParola.length()<6){
            auth_password.setError("Introduceti o parola de minim 6 caractere.");
            auth_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(textMail, textParola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    saveUserAuthCredentialsInSP(textMail, textParola);
                    intent = new Intent(AuthActivity.this, SportsListActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), R.string.auth_failed, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
