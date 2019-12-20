package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sportspot.util.Const;

public class TeamsReportActivity extends AppCompatActivity {
    private TextView chosenSportTV;
    private ListView results;
    private Intent intent;
    private String chosenSport = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_report);
        initComponents();
    }

    private void initComponents(){
        chosenSportTV = findViewById(R.id.teams_report_chosen_sport);
        intent = getIntent();
        chosenSport = intent.getStringExtra(Const.TEAMS_REPORT_INTENT_EXTRA);
        chosenSportTV.setText(chosenSport);

        results = findViewById(R.id.teams_report_results_listview);
    }
}
