package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private TextView comment;
    private TextView score;
    private Button add_feedback;
    private Button contact;
    private Button disconnect;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ProfileActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        add_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ProfileActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initComponents(){
        comment = findViewById(R.id.profile_comment);
        score = findViewById(R.id.profile_score);
        add_feedback = findViewById(R.id.profile_add_feedback);
        contact = findViewById(R.id.profile_contact);
        disconnect = findViewById(R.id.profile_disconnect);
    }
}
