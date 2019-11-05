package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
    private EditText auth_username;
    private EditText auth_password;
    private Button auth_connect;
    private TextView auth_signup;
    private Intent intent;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initComponents();

        auth_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AuthActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();


    }

    private void initComponents(){
        auth_username = findViewById(R.id.auth_username);
        auth_password = findViewById(R.id.auth_password);
        auth_connect = findViewById(R.id.auth_connect_button);
        auth_signup = findViewById(R.id.auth_signup_button);
    }

    
}
