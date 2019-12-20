package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportspot.database.service.TeamService;
import com.example.sportspot.database.tables.Team;
import com.example.sportspot.network.Game;
import com.example.sportspot.util.Const;
import com.example.sportspot.util.TeamAdapter;

import java.util.ArrayList;
import java.util.List;

public class TeamsReportActivity extends AppCompatActivity {
    private TextView chosenSportTV;
    private ListView results;
    private Intent intent;
    private String chosenSport = null;
    private ArrayList<Team> returnedTeams = new ArrayList<>();

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



        TeamAdapter teamAdapter = new TeamAdapter(getApplicationContext(), R.layout.teams_report_listview_item, returnedTeams, getLayoutInflater());
        results.setAdapter(teamAdapter);
        teamAdapter.notifyDataSetChanged();

        getTeams(chosenSport);
    }

    @SuppressLint("StaticFieldLeak")
    private void getTeams(String chosenSport){
        new TeamService.SelectTeamsBySport(getApplicationContext()){
            @Override
            protected void onPostExecute(List<Team> teams) {
                if(teams != null){
                    returnedTeams.clear();
                    returnedTeams.addAll(teams);

                    ArrayAdapter<Team> adapter = (ArrayAdapter<Team>) results.getAdapter();
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getApplicationContext(), returnedTeams.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }.execute(chosenSport);
    }
}
