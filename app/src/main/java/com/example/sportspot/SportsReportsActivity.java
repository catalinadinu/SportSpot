package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sportspot.database.dao.CoachDao;
import com.example.sportspot.database.service.CoachService;
import com.example.sportspot.database.tables.Coach;

import java.util.ArrayList;
import java.util.List;

public class SportsReportsActivity extends AppCompatActivity {
    List<Coach> coachesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_reports);

        getCoachesFromDB();
        Toast.makeText(getApplicationContext(), coachesList.toString(), Toast.LENGTH_LONG).show();
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
