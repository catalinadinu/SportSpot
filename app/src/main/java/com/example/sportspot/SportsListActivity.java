package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sportspot.network.Game;
import com.example.sportspot.network.HttpManager;
import com.example.sportspot.network.HttpResponse;
import com.example.sportspot.network.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class SportsListActivity extends AppCompatActivity {

    private ListView sportsListView;
    private List<Game> selectedGame = new ArrayList<>();

    private ImageView profile;
    private ImageView reports;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_list);
        initComponents();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SportsListActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SportsListActivity.this, SportsReportsActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<CharSequence> sportsAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.lv_sports_list,
                android.R.layout.simple_list_item_1);
        sportsListView.setAdapter(sportsAdapter);


        sportsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        intent = new Intent(SportsListActivity.this, FootballGamesActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(SportsListActivity.this, HandballGamesActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(SportsListActivity.this, VolleyballGamesActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

    }

    private void initComponents(){
        sportsListView = findViewById(R.id.sports_list_lv);
        profile = findViewById(R.id.sports_list_profile_button);
        reports = findViewById(R.id.sports_list_reports_button);
    }


}
