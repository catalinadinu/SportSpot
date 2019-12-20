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
import com.example.sportspot.database.tables.Team;
import com.example.sportspot.network.Game;

import java.util.List;

public class TeamAdapter extends ArrayAdapter<Team> {
    private Context context;
    private int resource;
    private List<Team> teams;
    private LayoutInflater inflater;

    public TeamAdapter(@NonNull Context context, int resource, List<Team> teams, LayoutInflater inflater) {
        super(context, resource, teams);
        this.context = context;
        this.resource = resource;
        this.teams = teams;
        this.inflater = inflater;
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View view = inflater.inflate(resource, parent,false);
//        Game match = matches.get(position);
//        if(match != null){
//            addTeamNames(view, match.getMeci());
//            addScore(view, match.getScor());
//
//            ExtraInfo extraInfo = match.getExtraInfo();
//            Date matchDate = extraInfo.getData();
//            Integer matchRedCardNumber = extraInfo.getCartonaseRosii();
//            String matchLocation = extraInfo.getLocatie();
//
//            addDate(view, matchDate);
//            addRedCards(view, matchRedCardNumber);
//            addLocation(view, matchLocation);
//
//        }
//
//        return view;
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Team team = teams.get(position);
        if(team != null){
            addTeamName(view, team.getTeamName());
            addTeamLeague(view, team.getLeague());
            addTeamVictories(view, team.getVictories());
            addTeamPoints(view, team.getPoints());
            addTeamRedCards(view, team.getRedCards());
            addTeamTitles(view, team.getTitlesNumber());
        }

        return view;
    }

    private void addTeamName(View view, String teamName){
        TextView tv = view.findViewById(R.id.tv_team_name);
        if(teamName != null && !teamName.trim().isEmpty()){
            tv.setText(teamName);
        }
        else {
            tv.setText(R.string.listview_row_field_no_content);
        }
    }

    private void addTeamLeague(View view, String league){
        TextView tv = view.findViewById(R.id.tv_team_league);
        if(league != null && !league.trim().isEmpty()){
            tv.setText(league);
        }
        else {
            tv.setText(R.string.listview_row_field_no_content);
        }
    }

    private void addTeamVictories(View view, Integer victories){
        TextView tv = view.findViewById(R.id.tv_team_victories);
        if(victories != null){
            String vict = "Victorii: " + victories.toString();
            tv.setText(vict);
        }
        else{
            tv.setText(R.string.listview_row_field_no_content);
        }
    }

    private void addTeamPoints(View view, Integer points){
        TextView tv = view.findViewById(R.id.tv_team_points);
        if(points != null){
            String pts = "Puncte: " + points.toString();
            tv.setText(pts);
        }
        else{
            tv.setText(R.string.listview_row_field_no_content);
        }
    }

    private void addTeamRedCards(View view, Integer redCardsNumber){
        TextView tv = view.findViewById(R.id.tv_team_redcards);
        if(redCardsNumber != null){
            String redCards = "Cartonase rosii: " + redCardsNumber.toString();
            tv.setText(redCards);
        }
        else{
            tv.setText(R.string.listview_row_field_no_content);
        }
    }

    private void addTeamTitles(View view, Integer teamTitles){
        TextView tv = view.findViewById(R.id.tv_team_titles);
        if(teamTitles != null){
            String titles = "Titluri: " + teamTitles.toString();
            tv.setText(titles);
        }
        else{
            tv.setText(R.string.listview_row_field_no_content);
        }
    }
}
