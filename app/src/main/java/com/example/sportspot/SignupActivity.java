package com.example.sportspot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private EditText signup_username;
    private EditText signup_password;
    private EditText signup_confirm_password;
    private Button signup_create_account_button;
    private TextView signup_auth_button;
    Intent intent;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initComponents();

        signup_auth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SignupActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        signup_create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authUser();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
//            user deja autentificat
        }

    }

    private void initComponents(){
        signup_username = findViewById(R.id.signup_username);
        signup_password = findViewById(R.id.signup_password);
        signup_confirm_password = findViewById(R.id.signup_confirm_password);
        signup_create_account_button = findViewById(R.id.signup_create_account_button);
        signup_auth_button = findViewById(R.id.signup_auth_button);

    }

    private void authUser(){
        final String textUsername = signup_username.getText().toString().trim();
        final String textPassword = signup_password.getText().toString().trim();
        String textConfirmPassword = signup_confirm_password.getText().toString().trim();

        if(textUsername.isEmpty()){
            signup_username.setError("Introduceti un email.");
            signup_username.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(textUsername).matches()){
            signup_username.setError("Introduceti o adresa email valida.");
            signup_username.requestFocus();
            return;
        }

        if(textPassword.isEmpty()){
            signup_password.setError("Introduceti o parola");
            signup_password.requestFocus();
            return;
        }

        if(textPassword.length()<6){
            signup_password.setError("Introduceti o parola de minim 6 caractere.");
            signup_password.requestFocus();
            return;
        }

        if(textConfirmPassword.isEmpty()){
            signup_confirm_password.setError("Introduceti parola pentru confirmare.");
            signup_confirm_password.requestFocus();
            return;
        }

        if(!textPassword.matches(textConfirmPassword)){
            signup_confirm_password.setError("Parolele introduse nu coincid.");
            signup_confirm_password.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(textUsername, textPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Inregistrarea s-a efectuat cu succes, se updateaza interfata
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Inregistrarea s-a efectuat cu succes!", Toast.LENGTH_SHORT).show();
                            intent = new Intent(SignupActivity.this, SportsListActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            //Eroare la inregistrare
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(), "Acest username este deja asociat unui cont.", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }

                    }
                });
    }



}
