package com.example.sportspot.database.tables;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "coaches",
        indices = {@Index(value = "curent_coached_team_id", unique = true)},
        foreignKeys = @ForeignKey(entity = Team.class, parentColumns = "id", childColumns = "curent_coached_team_id", onDelete = CASCADE))
public class Coach implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "sport")
    private String sport;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "age")
    private Integer age;

    @ColumnInfo(name = "coaching_experience")
    private Integer coachingExperience;

    @ColumnInfo(name = "curent_coached_team_id")
    private long currentCoachedTeamId;

    public Coach(long id, String sport, String name, Integer age, Integer coachingExperience, long currentCoachedTeamId) {
        this.id = id;
        this.sport = sport;
        this.name = name;
        this.age = age;
        this.coachingExperience = coachingExperience;
        this.currentCoachedTeamId = currentCoachedTeamId;
    }

    private Coach(Parcel in) {
        id = in.readLong();
        sport = in.readString();
        name = in.readString();
        if (in.readByte() == 0) {
            age = null;
        } else {
            age = in.readInt();
        }
        if (in.readByte() == 0) {
            coachingExperience = null;
        } else {
            coachingExperience = in.readInt();
        }
        currentCoachedTeamId = in.readLong();
    }

    public static final Creator<Coach> CREATOR = new Creator<Coach>() {
        @Override
        public Coach createFromParcel(Parcel in) {
            return new Coach(in);
        }

        @Override
        public Coach[] newArray(int size) {
            return new Coach[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCoachingExperience() {
        return coachingExperience;
    }

    public void setCoachingExperience(Integer coachingExperience) {
        this.coachingExperience = coachingExperience;
    }

    public long getCurrentCoachedTeamId() {
        return currentCoachedTeamId;
    }

    public void setCurrentCoachedTeamId(long currentCoachedTeamId) {
        this.currentCoachedTeamId = currentCoachedTeamId;
    }



    @Override
    public String toString() {
        return "Coach{" +
                "id=" + id +
                ", sport='" + sport + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", coachingExperience=" + coachingExperience +
                ", currentCoachedTeamId='" + currentCoachedTeamId + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(sport);
        parcel.writeString(name);
        if (age == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(age);
        }
        if (coachingExperience == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(coachingExperience);
        }
        parcel.writeLong(currentCoachedTeamId);
    }
}
