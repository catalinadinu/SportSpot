package com.example.sportspot.database.tables;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "teams")
public class Team implements Parcelable {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "sport")
    private String sport;

    @ColumnInfo(name = "team_name")
    private String teamName;

    @ColumnInfo(name = "league")
    private String league;

    @ColumnInfo(name = "victories")
    private Integer victories;

    @ColumnInfo(name = "points")
    private Integer points;

    @ColumnInfo(name = "red_cards")
    private Integer redCards;

    @ColumnInfo(name = "titles_number")
    private Integer titlesNumber;

    public Team(String id, String sport, String teamName, String league, Integer victories, Integer points, Integer redCards, Integer titlesNumber) {
        this.id = id;
        this.sport = sport;
        this.teamName = teamName;
        this.league = league;
        this.victories = victories;
        this.points = points;
        this.redCards = redCards;
        this.titlesNumber = titlesNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public Integer getVictories() {
        return victories;
    }

    public void setVictories(Integer victories) {
        this.victories = victories;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getRedCards() {
        return redCards;
    }

    public void setRedCards(Integer redCards) {
        this.redCards = redCards;
    }

    public Integer getTitlesNumber() {
        return titlesNumber;
    }

    public void setTitlesNumber(Integer titlesNumber) {
        this.titlesNumber = titlesNumber;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", sport='" + sport + '\'' +
                ", teamName='" + teamName + '\'' +
                ", league='" + league + '\'' +
                ", victories=" + victories +
                ", points=" + points +
                ", redCards=" + redCards +
                ", titlesNumber=" + titlesNumber +
                '}';
    }

    private Team(Parcel in) {
        id = in.readString();
        sport = in.readString();
        teamName = in.readString();
        league = in.readString();
        if (in.readByte() == 0) {
            victories = null;
        } else {
            victories = in.readInt();
        }
        if (in.readByte() == 0) {
            points = null;
        } else {
            points = in.readInt();
        }
        if (in.readByte() == 0) {
            redCards = null;
        } else {
            redCards = in.readInt();
        }
        if (in.readByte() == 0) {
            titlesNumber = null;
        } else {
            titlesNumber = in.readInt();
        }
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(sport);
        parcel.writeString(teamName);
        parcel.writeString(league);
        if (victories == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(victories);
        }
        if (points == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(points);
        }
        if (redCards == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(redCards);
        }
        if (titlesNumber == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(titlesNumber);
        }
    }
}
