package com.example.sportspot.database.service;

import android.content.Context;
import android.os.AsyncTask;

import com.example.sportspot.database.DBManager;
import com.example.sportspot.database.dao.TeamDao;
import com.example.sportspot.database.tables.Team;

import java.util.List;

public class TeamService {
    private static TeamDao teamDao;

    public static class GetAllTeams extends AsyncTask<Void, Void, List<Team>> {

        public GetAllTeams(Context context) {
            teamDao = DBManager.getInstance(context).getTeamDao();
        }

        @Override
        protected List<Team> doInBackground(Void... voids) {
            return teamDao.getAllTeams();
        }
    }

    public static class Insert extends AsyncTask<Team, Void, Team> {
        public Insert(Context context) {
            teamDao = DBManager.getInstance(context).getTeamDao();
        }

        @Override
        protected Team doInBackground(Team... teams) {
            if (teams == null || teams.length != 1) {
                return null;
            }
            Team team = teams[0];
            long id = teamDao.insert(team);
            if (id != -1) {
                team.setId(id);
                return team;
            }
            return null;
        }
    }

    public static class UpdateTeam extends AsyncTask<Team, Void, Integer> {
        public UpdateTeam(Context context) {
            teamDao = DBManager.getInstance(context).getTeamDao();
        }

        @Override
        protected Integer doInBackground(Team... teams) {
            if (teams == null || teams.length != 1) {
                return -1;
            }
            return teamDao.update(teams[0]);
        }
    }

    public static class DeleteTeam extends AsyncTask<Team, Void, Integer> {
        public DeleteTeam(Context context) {
            teamDao = DBManager.getInstance(context).getTeamDao();
        }

        @Override
        protected Integer doInBackground(Team... teams) {
            if (teams == null || teams.length != 1) {
                return -1;
            }
            return teamDao.delete(teams[0]);
        }
    }

    public static class SelectTeamsBySport extends AsyncTask<String,Void, List<Team>>{
        public SelectTeamsBySport(Context context){
            teamDao = DBManager.getInstance(context).getTeamDao();
        }

        @Override
        protected List<Team> doInBackground(String... strings) {
            if(strings == null || strings.length == 0){
                return null;
            }
            return teamDao.selectTeamsBySport(strings[0]);
        }
    }

    public static class SelectTeamsWithWonTitles extends AsyncTask<String, Void, List<Team>>{
        public SelectTeamsWithWonTitles(Context context){
            teamDao = DBManager.getInstance(context).getTeamDao();
        }

        @Override
        protected List<Team> doInBackground(String... strings) {
            if(strings == null || strings.length == 0){
                return null;
            }
            return teamDao.selectTeamsWithWonTitles(strings[0]);
        }
    }
}
