package com.example.sportspot.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sportspot.database.dao.CoachDao;
import com.example.sportspot.database.dao.TeamDao;
import com.example.sportspot.database.tables.Coach;
import com.example.sportspot.database.tables.Team;
import com.example.sportspot.util.Const;

@Database(entities = {Team.class, Coach.class}, version = 6, exportSchema = false)
public abstract class DBManager extends RoomDatabase {
    public static DBManager dbManager;

    private void prePopulateCoachTable(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(getCoachDao().countCoaches() == 0){
                    for(int i=0; i<Const.COACHES.length; i++){
                        getCoachDao().insertCoach(Const.COACHES[i]);
                    }
                }
            }
        }).start();

    }


    private void prePopulateTeamTable(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(getTeamDao().countTeams() == 0){
                    for(int i=0; i<Const.TEAMS.length; i++){
                        getTeamDao().insert(Const.TEAMS[i]);
                    }
                }
            }
        }).start();
    }

//    private void deleteData(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getTeamDao().nukeTable();
//                getCoachDao().nukeTable();
//            }
//        }).start();
//    }

    public static DBManager getInstance(Context context){
        if(dbManager == null){
            synchronized (DBManager.class){
                if(dbManager == null){
                    dbManager = Room.databaseBuilder(context, DBManager.class, Const.DB_NAME).fallbackToDestructiveMigration().build();
                    dbManager.prePopulateCoachTable();
                    dbManager.prePopulateTeamTable();
                    return dbManager;
                }
            }
        }
        return dbManager;
    }

    public abstract CoachDao getCoachDao();
    public abstract TeamDao getTeamDao();
}
