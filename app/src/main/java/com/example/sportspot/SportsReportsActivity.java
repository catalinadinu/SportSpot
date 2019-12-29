package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sportspot.database.dao.CoachDao;
import com.example.sportspot.database.service.CoachService;
import com.example.sportspot.database.tables.Coach;
import com.example.sportspot.util.Const;

import java.util.ArrayList;
import java.util.List;

public class SportsReportsActivity extends AppCompatActivity {
    List<Coach> coachesList = new ArrayList<>();
    private Spinner sportsSpinner;
    private Button teamsReport;
    private Button coachesReport;
    private Button titlesReport;
    private Button redCardsReport;
    private String chosenSport = null;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_reports);

        initComponents();
        getCoachesFromDB();

        teamsReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SportsReportsActivity.this, TeamsReportActivity.class);
                intent.putExtra(Const.TEAMS_REPORT_INTENT_EXTRA, chosenSport);
                startActivity(intent);
            }
        });

        coachesReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SportsReportsActivity.this, CoachesReportActivity.class);
                intent.putExtra(Const.COACHES_REPORT_INTENT_EXTRA, chosenSport);
                startActivity(intent);
            }
        });

        titlesReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SportsReportsActivity.this, TitlesReportActivity.class);
                intent.putExtra(Const.TITLES_REPORT_INTENT_EXTRA, chosenSport);
                startActivity(intent);
            }
        });

        redCardsReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SportsReportsActivity.this, RedCardsGraphReportActivity.class);
                intent.putExtra(Const.RED_CARDS_REPORT_INTENT_EXTRA, chosenSport);
                startActivity(intent);
            }
        });

    }

    private void initComponents(){
        sportsSpinner = findViewById(R.id.sports_reports_spinner);
        teamsReport = findViewById(R.id.sports_reports_teams_report);
        coachesReport = findViewById(R.id.sports_reports_coaches_report);
        titlesReport = findViewById(R.id.sports_reports_titles_report);
        redCardsReport = findViewById(R.id.sports_reports_red_cards_report);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.lv_sports_list, R.layout.support_simple_spinner_dropdown_item);
        sportsSpinner.setAdapter(spinnerAdapter);

        sportsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                chosenSport = sportsSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    @SuppressLint("StaticFieldLeak")
    private void getCoachesFromDB() {
        new CoachService.GetAllCoaches(getApplicationContext()){
            @Override
            protected void onPostExecute(List<Coach> coaches) {
                if(coaches != null){
                    coachesList.clear();
                    coachesList.addAll(coaches);
                }
            }
        }.execute();
    }
}
