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

    @Query("SELECT COUNT (*) FROM teams")
    int countTeams();

    @Query("SELECT * FROM teams WHERE sport LIKE :chosenSport")
    List<Team> selectTeamsBySport(String chosenSport);

    @Insert
    long insert(Team team);

    @Insert
    long[] insertAllTeams(Team[] teams);

    @Update
    int update(Team team);

    @Delete
    int delete(Team team);

    @Query("DELETE FROM teams")
    public void nukeTable();
}
