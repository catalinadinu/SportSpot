package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportspot.database.service.TeamService;
import com.example.sportspot.database.tables.Team;
import com.example.sportspot.network.Game;
import com.example.sportspot.util.Const;
import com.example.sportspot.util.TeamAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamsReportActivity extends AppCompatActivity {
    private TextView chosenSportTV;
    private ListView results;
    private Intent intent;
    private String chosenSport = null;
    private ArrayList<Team> returnedTeams = new ArrayList<>();
    private Button saveReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_report);
        initComponents();

        saveReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File directory = getFilesDir();
                File file = new File(directory, Const.TEAMS_REPORT_FILE_NAME);

                FileOutputStream fos = null;
                try{
                    fos = new FileOutputStream(file);
                    String sport = chosenSport + "\n";
                    fos.write(sport.getBytes());
                    for(Team t:returnedTeams){
                        String victories = "Victorii: " + t.getVictories() + "\n";
                        String points = "Puncte: " + t.getPoints() + "\n";
                        String redCards = "Cartonase rosii: " + t.getRedCards() + "\n";
                        String titles = "Titulri: " + t.getTitlesNumber() + "\n";
                        String teamName = t.getTeamName() + "\n";
                        String league = t.getLeague() + "\n";

                        fos.write(teamName.getBytes());
                        fos.write(league.getBytes());
                        fos.write(victories.getBytes());
                        fos.write(points.getBytes());
                        fos.write(redCards.getBytes());
                        fos.write(titles.getBytes());
                    }
                    Toast.makeText(getApplicationContext(), getString(R.string.datele_au_fost_salvate_la_adresa) + directory, Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initComponents(){
        chosenSportTV = findViewById(R.id.teams_report_chosen_sport);
        intent = getIntent();
        chosenSport = intent.getStringExtra(Const.TEAMS_REPORT_INTENT_EXTRA);
        chosenSportTV.setText(chosenSport);

        saveReport = findViewById(R.id.teams_report_save_button);

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
                }
            }
        }.execute(chosenSport);
    }
}
