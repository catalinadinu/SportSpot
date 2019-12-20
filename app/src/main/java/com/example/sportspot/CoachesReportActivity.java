package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportspot.database.service.CoachService;
import com.example.sportspot.database.tables.Coach;
import com.example.sportspot.util.CoachAdapter;
import com.example.sportspot.util.Const;

import java.util.ArrayList;
import java.util.List;

public class CoachesReportActivity extends AppCompatActivity {
    private TextView chosenSportTV;
    private ListView results;
    private Intent intent;
    private String chosenSport = null;
    private List<Coach> returnedCoaches = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coaches_report);
        initComponents();
    }

    private void initComponents(){
        chosenSportTV = findViewById(R.id.coaches_report_chosen_sport);
        intent = getIntent();
        chosenSport = intent.getStringExtra(Const.COACHES_REPORT_INTENT_EXTRA);
        chosenSportTV.setText(chosenSport);

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

                    Toast.makeText(getApplicationContext(), returnedCoaches.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }.execute(chosenSport);
    }
}
