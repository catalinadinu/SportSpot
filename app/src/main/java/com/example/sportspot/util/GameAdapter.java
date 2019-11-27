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
import com.example.sportspot.network.ExtraInfo;
import com.example.sportspot.network.Game;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GameAdapter extends ArrayAdapter<Game> {
    private Context context;
    private int resource;
    private List<Game> matches;
    private LayoutInflater inflater;


    public GameAdapter(@NonNull Context context,
                       int resource,
                       List<Game> matches,
                       LayoutInflater inflater ) {
        super(context, resource, matches);
        this.context = context;
        this.resource = resource;
        this.matches = matches;
        this.inflater = inflater;
    }

//    public GameAdapter(@NonNull Context context,
//                       int resource,
//                       List<Game> matches) {
//        super(context, resource, matches);
//        this.context = context;
//        this.resource = resource;
//        this.matches = matches;
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent,false);
        Game match = matches.get(position);
        if(match != null){
            addTeamNames(view, match.getMeci());
            addScore(view, match.getScor());

            ExtraInfo extraInfo = match.getExtraInfo();
            Date matchDate = extraInfo.getData();
            addDate(view, matchDate);
        }

        return view;
    }

    private void addTeamNames(View view, String teamNames){
        TextView tv = view.findViewById(R.id.lv_teams);
        if(teamNames != null && !teamNames.trim().isEmpty()){
            tv.setText(teamNames);
        }
        else {
            tv.setText(R.string.listview_row_field_no_content);
        }
    }

    private void addScore(View view, String score){
        TextView tv = view.findViewById(R.id.lv_game_score);
        if(score != null && !score.trim().isEmpty()){
            tv.setText(score);
        }
        else {
            tv.setText(R.string.listview_row_field_no_content);
        }
    }

    private void addDate(View view, Date matchDate){
        TextView tv = view.findViewById(R.id.lv_game_date);
        if(matchDate != null){
            tv.setText(new SimpleDateFormat(Const.DATE_FORMAT, Locale.US)
                    .format(matchDate));
        }
        else{
            tv.setText(R.string.listview_row_field_no_content);
        }
    }
}
