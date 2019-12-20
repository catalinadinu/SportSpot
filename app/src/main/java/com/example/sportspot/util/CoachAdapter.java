package com.example.sportspot.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sportspot.R;
import com.example.sportspot.database.tables.Coach;
import com.example.sportspot.network.Game;

import java.util.List;

public class CoachAdapter extends ArrayAdapter<Coach> {
    private Context context;
    private int resource;
    private List<Coach> coaches;
    private LayoutInflater inflater;

    public CoachAdapter(@NonNull Context context,int resource,List<Coach> coaches, LayoutInflater inflater ) {
        super(context, resource, coaches);
        this.context = context;
        this.resource = resource;
        this.coaches = coaches;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Coach coach = coaches.get(position);
        if(coach != null){
            addCoachName(view, coach.getName());
//            addCoachTeam();
            addCoachAge(view, coach.getAge());
            addCoachexperience(view, coach.getCoachingExperience());
        }
        return view;
    }

    private void addCoachName(View view, String coachName){
        TextView tv = view.findViewById(R.id.tv_coach_name);
        if(coachName != null && !coachName.trim().isEmpty()){
            tv.setText(coachName);
        }
        else{
            tv.setText(R.string.listview_row_field_no_content);
        }
    }

    private void addCoachTeam(View view, String team){
        TextView tv = view.findViewById(R.id.tv_coach_team);
        if(team != null && !team.trim().isEmpty()){
            tv.setText(team);
        }
        else {
            tv.setText(R.string.listview_row_field_no_content);
        }
    }

    private void addCoachAge(View view, Integer coachAge){
        TextView tv = view.findViewById(R.id.tv_coach_age);
        if(coachAge != null){
            String age = "Varsta: " + coachAge.toString();
            tv.setText(age);
        }
        else{
            tv.setText(R.string.listview_row_field_no_content);
        }
    }

    private void addCoachexperience(View view, Integer coachExperience){
        TextView tv = view.findViewById(R.id.tv_coach_experience);
        if(coachExperience != null){
            String experience = "Experienta: " + coachExperience.toString();
            tv.setText(experience);
        }
        else{
            tv.setText(R.string.listview_row_field_no_content);
        }
    }
}
