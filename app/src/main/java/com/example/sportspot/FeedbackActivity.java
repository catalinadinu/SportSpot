package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sportspot.util.Feedback;

public class FeedbackActivity extends AppCompatActivity {
    public static final String ADD_FEEDBACK_KEY = "AddFeedbackKey";

    private EditText comentariu;
    private EditText nota;
    private Button saveFeedback;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initComponents();

        intent = getIntent();

        saveFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valid()){
                    Feedback feedback = createFeedbackFromView();
                    Toast.makeText(getApplicationContext(), "Date preluate cu succes!", Toast.LENGTH_SHORT).show();
                    intent.putExtra(ADD_FEEDBACK_KEY, feedback);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void initComponents(){
        comentariu = findViewById(R.id.feedback_comment);
        nota = findViewById(R.id.feedback_score);
        saveFeedback = findViewById(R.id.feedback_add);
    }

    private boolean valid(){
        if(comentariu.getText() == null || comentariu.getText().toString().trim().isEmpty()){
            comentariu.setError("Introduceti un comentariu!");
            comentariu.requestFocus();
            return false;
        }

        if(nota.getText() == null || nota.getText().toString().trim().isEmpty() ||
            Integer.parseInt(String.valueOf(nota.getText())) < 1 ||
            Integer.parseInt(String.valueOf(nota.getText())) >10){
                nota.setError("Nota max: 10, nota min: 1");
                nota.requestFocus();
                return false;
        }
        return true;
    }

    private Feedback createFeedbackFromView(){
        String commentText = comentariu.getText().toString();
        int score = Integer.parseInt(nota.getText().toString());

        return new Feedback(commentText, score);
    }
}
