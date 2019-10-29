package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class SportsListActivity extends AppCompatActivity {
    private ListView sportsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_list);
        initComponents();

        ArrayAdapter<CharSequence> sportsAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.lv_sports_list,
                android.R.layout.simple_list_item_1);
        sportsListView.setAdapter(sportsAdapter);

    }

    private void initComponents(){
        sportsListView = findViewById(R.id.sports_list_lv);
    }


}
