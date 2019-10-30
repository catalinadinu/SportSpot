package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {
    private EditText signup_username;
    private EditText signup_password;
    private EditText signup_confirm_password;
    private Button signup_create_account_button;
    private TextView signup_auth_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initComponents();
    }

    private void initComponents(){
        signup_username = findViewById(R.id.signup_username);
        signup_password = findViewById(R.id.signup_password);
        signup_confirm_password = findViewById(R.id.signup_confirm_password);
        signup_create_account_button = findViewById(R.id.signup_create_account_button);
        signup_auth_button = findViewById(R.id.signup_auth_button);

    }
}
