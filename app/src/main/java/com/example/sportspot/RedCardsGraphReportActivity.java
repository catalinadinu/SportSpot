package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportspot.database.service.TeamService;
import com.example.sportspot.database.tables.Team;
import com.example.sportspot.util.Const;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class RedCardsGraphReportActivity extends AppCompatActivity {
    private TextView chosenSportTV;
    private Intent intent;
    private String chosenSport = null;
    private TextView teamNamesTV;
    private ArrayList<Team> returnedTeams = new ArrayList<>();
    private ArrayList<String> teamCodes = new ArrayList<>();
    private ArrayList<BarEntry> redCardsEntries = new ArrayList<>();

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_cards_graph_report);
        initComponents();
    }

    private void initComponents(){
        chosenSportTV = findViewById(R.id.red_cards_report_chosen_sport);
        barChart = findViewById(R.id.red_cards_report_graph);
        intent = getIntent();
        chosenSport = intent.getStringExtra(Const.RED_CARDS_REPORT_INTENT_EXTRA);
        chosenSportTV.setText(chosenSport);
        teamNamesTV = findViewById(R.id.red_cards_report_team_names);
        getTeams(chosenSport);
    }

    @SuppressLint("StaticFieldLeak")
    private void getTeams(String chosenSport){
        new TeamService.SelectTeamsBySport(getApplicationContext()){
            @Override
            protected void onPostExecute(List<Team> teams) {
                if(teams != null && !teams.isEmpty()){
                    returnedTeams.clear();
                    returnedTeams.addAll(teams);

                    String teamNamesResult = "";
                    int i = 0;
                    for(Team t:returnedTeams){
                        teamCodes.add(t.getCode());
                        redCardsEntries.add(new BarEntry(i, t.getRedCards()));
                        i++;
                        teamNamesResult = teamNamesResult + t.getCode() + " - " + t.getTeamName() + "\n";
                    }

                    BarDataSet barDataSet = new BarDataSet(redCardsEntries, "Cartonase rosii");
                    barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
                    BarData barData = new BarData(barDataSet);
                    barData.setValueFormatter(new LargeValueFormatter());

                    barChart.setData(barData);
                    barChart.setTouchEnabled(true);
                    barChart.setDragEnabled(true);
                    barChart.setScaleEnabled(true);
                    barChart.getDescription().setText("Reprezentarea grafica a numarului de cartonase rosii obtinut de fiecare echipa");

                    XAxis xAxis = barChart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(teamCodes));

                    teamNamesTV.setText(teamNamesResult);
                }
            }
        }.execute(chosenSport);
    }
}
