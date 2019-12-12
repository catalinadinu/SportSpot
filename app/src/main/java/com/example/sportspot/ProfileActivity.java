package com.example.sportspot;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportspot.util.Const;
import com.example.sportspot.util.Feedback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import static com.example.sportspot.FeedbackActivity.ADD_FEEDBACK_KEY;

public class ProfileActivity extends AppCompatActivity {
    private TextView comment;
    private TextView score;
    private Button add_feedback;
    private Button contact;
    private Button disconnect;
    private Intent intent;
    private Feedback feedback;
    private ArrayList<Feedback> feedbackList = new ArrayList<>();


    public static final int REQUEST_CODE_ADD_FEEDBACK = 1;


//    private static final String SP_FILE_NAME = "feedbackSharedPreferences";
//    private static final String SP_COMMENT = "sharedPreferencesComment";
//    public static final String SP_SCORE = "sharedPreferencesScore";
//    public static final String SP_USER = "sharedPreferenesUser";


    private SharedPreferences sp;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();

        Intent data = getIntent();
//        Feedback feedback = data.getParcelableExtra(ADD_FEEDBACK_KEY);
//        if(feedback != null) {
//            String comentariu = feedback.getComentariu();
//            String nota = String.valueOf(feedback.getNota());
//            comment.setText(comentariu);
//            score.setText(nota);
//        }

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
                startActivityForResult(intent, REQUEST_CODE_ADD_FEEDBACK);
            }
        });

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = getSharedPreferences(Const.SP_FILE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove(Const.SP_EMAIL_KEY);
                editor.remove(Const.SP_PASSWORD_KEY);
                editor.apply();

                FirebaseAuth.getInstance().signOut();

                intent = new Intent(ProfileActivity.this, AuthActivity.class);
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

//        if(feedbackList != null && feedback != null){
//            comment.setText(feedback.getComentariu());
//            score.setText(feedback.getNota());
//        }

//        sp = getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
//
//        //citire din fisierul sp
//        String commentFeedback = sp.getString(SP_COMMENT, "");
//        String scoreFeedback = sp.getString(SP_SCORE, "");
//        String emailFeedback = sp.getString(SP_USER, "");
//
//        //populare text elemente vizuale
//        String userEmail = null;
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user != null){
//            userEmail = user.getEmail();
//        }

//        if(!commentFeedback.isEmpty() && !scoreFeedback.isEmpty()){
//            if(emailFeedback.equals(userEmail)){
//                comment.setText(commentFeedback);
//                score.setText(scoreFeedback);
//            }
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_FEEDBACK && resultCode == RESULT_OK && data != null){
            feedback = data.getParcelableExtra(ADD_FEEDBACK_KEY);
            if(feedback != null){
                Toast.makeText(getApplicationContext(), "Feedback-ul a fost transmis cu succes.", Toast.LENGTH_LONG).show();
                String comentariu = feedback.getComentariu();
                String nota = String.valueOf(feedback.getNota());
                comment.setText(comentariu);
                score.setText(nota);
                feedbackList.add(feedback);

//                String email = null;
//                user = FirebaseAuth.getInstance().getCurrentUser();
//                if(user != null){
//                    email = user.getEmail();
//                }

//                SharedPreferences.Editor editor = sp.edit();
//                editor.putString(SP_COMMENT, comentariu);
//                editor.putString(SP_SCORE, nota);
//                editor.putString(SP_USER, email);
//                editor.apply();

            }
            else {
                Toast.makeText(getApplicationContext(), "A aparut o eroare la transmiterea feedback-ului..", Toast.LENGTH_LONG).show();
            }

        }
    }
}
