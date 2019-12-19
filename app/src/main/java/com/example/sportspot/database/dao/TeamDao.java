package com.example.sportspot.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sportspot.database.tables.Team;

import java.util.List;

@Dao
public interface TeamDao {
    @Query("select * from teams")
    List<Team> getAllTeams();

    @Insert
    String insert(Team team);

    @Insert
    void insertAllTeams(Team... teams);

    @Update
    int update(Team team);

    @Delete
    int delete(Team team);
}
