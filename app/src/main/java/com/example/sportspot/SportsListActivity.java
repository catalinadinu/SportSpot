package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

public class SportsListActivity extends AppCompatActivity {
    private ListView sportsListView;
    private ImageView profile;
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

        ArrayAdapter<CharSequence> sportsAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.lv_sports_list,
                android.R.layout.simple_list_item_1);
        sportsListView.setAdapter(sportsAdapter);



    }

    private void initComponents(){
        sportsListView = findViewById(R.id.sports_list_lv);
        profile = findViewById(R.id.sports_list_profile_button);
    }


}
