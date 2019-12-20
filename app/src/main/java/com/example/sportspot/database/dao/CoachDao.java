package com.example.sportspot.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sportspot.database.tables.Coach;

import java.util.List;

@Dao
public interface CoachDao {
    @Query("select * from coaches")
    List<Coach> getAllCoaches();

    @Query("SELECT COUNT (*) FROM coaches")
    int countCoaches();

    @Insert
    long insertCoach(Coach coach);

    @Insert
    long[] insertAllCoaches(Coach[] coaches);

    @Update
    int updateCoach(Coach coach);

    @Delete
    int deleteCoach(Coach coach);

    @Query("DELETE FROM coaches")
    public void nukeTable();
}
