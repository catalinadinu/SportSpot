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
    private static final String URL = "https://api.myjson.com/bins/1dfcnu";
    private HttpResponse httpResponse;

    private ListView sportsListView;
    private List<Game> selectedGame = new ArrayList<>();

    private ImageView profile;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_list);
        initComponents();

        new HttpManager(){
            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(getApplicationContext(), "Se preiau datele...", Toast.LENGTH_SHORT).show();
                httpResponse = JsonParser.parseJson(s);
                if(httpResponse != null){
                    Toast.makeText(getApplicationContext(), "Datele despre rezultate au fost preluate.", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(URL);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SportsListActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<CharSequence> sportsAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.lv_sports_list,
                android.R.layout.simple_list_item_1);
        sportsListView.setAdapter(sportsAdapter);

//        sportsListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

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
    }


}
