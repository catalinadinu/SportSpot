package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sportspot.network.Game;
import com.example.sportspot.network.HttpManager;
import com.example.sportspot.network.HttpResponse;
import com.example.sportspot.network.JsonParser;
import com.example.sportspot.util.GameAdapter;

import java.util.ArrayList;
import java.util.List;

public class FootballGamesActivity extends AppCompatActivity {
    private static final String URL = "https://api.myjson.com/bins/1dfcnu";
    private HttpResponse httpResponse;

    private ListView lvFootballGames;
    private ArrayList<Game> footballResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_games);

        initComponents();

        new HttpManager(){
            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(getApplicationContext(), R.string.se_preiau_datele, Toast.LENGTH_SHORT).show();
                httpResponse = JsonParser.parseJson(s);
                if(httpResponse != null){
                    Toast.makeText(getApplicationContext(), R.string.datele_au_fost_preluate_cu_succes, Toast.LENGTH_SHORT).show();
                }

                if (httpResponse != null && httpResponse.getFotbal() != null){
                    selectGame(httpResponse.getFotbal());
                }
            }
        }.execute(URL);
    }

    private void initComponents(){
        lvFootballGames = findViewById(R.id.lv_football_results);

        if(getApplicationContext() != null) {
            GameAdapter gameAdapter = new GameAdapter(getApplicationContext(), R.layout.listview_row_view, footballResults, getLayoutInflater());
            lvFootballGames.setAdapter(gameAdapter);
        }

    }

    private void selectGame(List<Game> list){
        footballResults.clear();
        footballResults.addAll(list);
        ArrayAdapter<Game> adapter = (ArrayAdapter<Game>) lvFootballGames.getAdapter();
        adapter.notifyDataSetChanged();
    }
}
