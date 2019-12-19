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

@Database(entities = {Team.class, Coach.class}, version = 1, exportSchema = false)
public abstract class DBManager extends RoomDatabase {
    public static DBManager dbManager;

    public abstract CoachDao getCoachDao();
    public abstract TeamDao getTeamDao();

    private void prePopulateCoachTable(){
        if(getCoachDao().countCoaches() == 0){
            for(int i=0; i<Const.COACHES.length; i++){
                getCoachDao().insertCoach(Const.COACHES[i]);
            }
        }
    }


    private void prePopulateTeamTable(){
        if(getTeamDao().countTeams() == 0){
            for(int i=0; i<Const.TEAMS.length; i++){
                getTeamDao().insert(Const.TEAMS[i]);
            }
        }
    }



    public static DBManager getInstance(Context context){
        if(dbManager == null){
            synchronized (DBManager.class){
                if(dbManager == null){
                    dbManager = Room.databaseBuilder(context, DBManager.class, Const.DB_NAME).fallbackToDestructiveMigration().build();
//                    dbManager.prePopulateCoachTable();
//                    dbManager.prePopulateTeamTable();
                    return dbManager;
                }
            }
        }
        return dbManager;
    }


}
