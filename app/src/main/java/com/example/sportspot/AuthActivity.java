package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AuthActivity extends AppCompatActivity {
    private EditText auth_username;
    private EditText auth_password;
    private Button auth_connect;
    private TextView auth_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initComponents();
    }

    private void initComponents(){
        auth_username = findViewById(R.id.auth_username);
        auth_password = findViewById(R.id.auth_password);
        auth_connect = findViewById(R.id.auth_connect_button);
        auth_signup = findViewById(R.id.auth_signup_button);
    }
}
