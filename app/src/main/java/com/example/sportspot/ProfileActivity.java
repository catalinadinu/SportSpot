package com.example.sportspot;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    private SharedPreferences sp;


    private DatabaseReference mDatabase;

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

        mDatabase = FirebaseDatabase.getInstance().getReference("feedback").push();

        getFeedbackFromFirebase();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_FEEDBACK && resultCode == RESULT_OK && data != null){
            feedback = data.getParcelableExtra(ADD_FEEDBACK_KEY);
            if(feedback != null){
                Toast.makeText(getApplicationContext(), R.string.feedback_succes, Toast.LENGTH_LONG).show();
                String comentariu = feedback.getComentariu();
                String nota = String.valueOf(feedback.getNota());
                comment.setText(comentariu);
                score.setText(nota);

                String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                feedback.setUser(user);

                mDatabase.setValue(feedback);

            }
            else {
                Toast.makeText(getApplicationContext(), R.string.feedback_fail, Toast.LENGTH_LONG).show();
            }

        }
    }

    private void getFeedbackFromFirebase(){
        FirebaseDatabase.getInstance().getReference().child("feedback").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){

                    String user = child.getValue(Feedback.class).getUser();
                    String comment = child.getValue(Feedback.class).getComentariu();
                    int score = child.getValue(Feedback.class).getNota();
                    Feedback f = new Feedback();
                    f.setComentariu(comment);
                    f.setNota(score);
                    f.setUser(user);

                    if (user.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                        feedbackList.add(f);
                    }
                }
                if(feedbackList != null && !feedbackList.isEmpty()){
                    int position = feedbackList.size() - 1;
                    feedback = feedbackList.get(position);
                    String c = "Comentariu: " + feedback.getComentariu();
                    String s = "Nota: " + String.valueOf(feedback.getNota());
                    comment.setText(c);
                    score.setText(s);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
