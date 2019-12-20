package com.example.sportspot.database.service;

import android.content.Context;
import android.os.AsyncTask;

import com.example.sportspot.database.DBManager;
import com.example.sportspot.database.dao.CoachDao;
import com.example.sportspot.database.tables.Coach;

import java.util.List;

public class CoachService {
    private static CoachDao coachDao;

    public static class GetAllCoaches extends AsyncTask<Void, Void, List<Coach>>{

        public GetAllCoaches(Context context){
            coachDao = DBManager.getInstance(context).getCoachDao();
        }

        @Override
        protected List<Coach> doInBackground(Void... voids) {
            return coachDao.getAllCoaches();
        }
    }

    public static class InsertCoach extends AsyncTask<Coach, Void, Coach>{

        public InsertCoach(Context context){
            coachDao = DBManager.getInstance(context).getCoachDao();
        }

        @Override
        protected Coach doInBackground(Coach... coaches) {
            if(coaches == null || coaches.length != 1){
                return null;
            }
            Coach coach = coaches[0];
            long id = coachDao.insertCoach(coach);
            if(id != -1){
                coach.setId(id);
                return coach;
            }
            return null;
        }
    }

    public static class UpdateCoach extends AsyncTask<Coach, Void, Integer> {
        public UpdateCoach(Context context) {
            coachDao = DBManager
                    .getInstance(context)
                    .getCoachDao();
        }

        @Override
        protected Integer doInBackground(Coach... coaches) {
            if (coaches == null || coaches.length != 1) {
                return -1;
            }
            return coachDao.updateCoach(coaches[0]);
        }
    }

    public static class DeleteCoach extends AsyncTask<Coach, Void, Integer> {
        public DeleteCoach(Context context) {
            coachDao = DBManager.getInstance(context).getCoachDao();
        }

        @Override
        protected Integer doInBackground(Coach... coaches) {
            if (coaches == null || coaches.length != 1) {
                return -1;
            }
            return coachDao.deleteCoach(coaches[0]);
        }
    }

    public static class SelectCoachesBySport extends AsyncTask<String, Void, List<Coach>>{
        public SelectCoachesBySport(Context context){
            coachDao = DBManager.getInstance(context).getCoachDao();
        }

        @Override
        protected List<Coach> doInBackground(String... strings) {
            if(strings == null || strings.length == 0){
                return null;
            }
            return coachDao.selectCoachesBySport(strings[0]);
        }
    }
}
