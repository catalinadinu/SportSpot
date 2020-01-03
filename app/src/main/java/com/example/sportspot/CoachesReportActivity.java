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

import com.example.sportspot.database.service.CoachService;
import com.example.sportspot.database.tables.Coach;
import com.example.sportspot.database.tables.Team;
import com.example.sportspot.util.CoachAdapter;
import com.example.sportspot.util.Const;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoachesReportActivity extends AppCompatActivity {
    private TextView chosenSportTV;
    private ListView results;
    private Intent intent;
    private String chosenSport = null;
    private List<Coach> returnedCoaches = new ArrayList<>();
    private Button saveReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coaches_report);
        initComponents();

        saveReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File directory = getFilesDir();
                File file = new File(directory, Const.COACHES_REPORT_FILE_NAME);

                FileOutputStream fos = null;
                try{
                    fos = new FileOutputStream(file);
                    String sport = chosenSport + "\n";
                    fos.write(sport.getBytes());
                    for(Coach c:returnedCoaches){
                        String age = "Varsta: " + c.getAge() + "\n";
                        String experience = "Experienta: " + c.getCoachingExperience() + "\n";
                        String name = c.getName() + "\n";
                        String coachedTeam = c.getCurrentCoachedTeamName() + "\n";

                        fos.write(name.getBytes());
                        fos.write(coachedTeam.getBytes());
                        fos.write(age.getBytes());
                        fos.write(experience.getBytes());
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
        chosenSportTV = findViewById(R.id.coaches_report_chosen_sport);
        intent = getIntent();
        chosenSport = intent.getStringExtra(Const.COACHES_REPORT_INTENT_EXTRA);
        chosenSportTV.setText(chosenSport);

        saveReport = findViewById(R.id.coaches_report_save_button);

        results = findViewById(R.id.coaches_report_results_listview);

        CoachAdapter coachAdapter = new CoachAdapter(getApplicationContext(), R.layout.coaches_report_listview_item, returnedCoaches, getLayoutInflater());
        results.setAdapter(coachAdapter);
        coachAdapter.notifyDataSetChanged();

        getCoaches(chosenSport);
    }

    @SuppressLint("StaticFieldLeak")
    private void getCoaches(String chosenSport){
        new CoachService.SelectCoachesBySport(getApplicationContext()){
            @Override
            protected void onPostExecute(List<Coach> coaches) {
                if(coaches != null){
                    returnedCoaches.clear();
                    returnedCoaches.addAll(coaches);

                    ArrayAdapter<Coach> adapter = (ArrayAdapter<Coach>) results.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        }.execute(chosenSport);
    }
}
