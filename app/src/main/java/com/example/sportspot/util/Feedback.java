package com.example.sportspot.util;

import android.os.Parcel;
import android.os.Parcelable;

public class Feedback implements Parcelable {
    private String comentariu;
    private int nota;
    private String user;

    public Feedback(){

    }

    public Feedback(String comentariu, int nota) {
        this.comentariu = comentariu;
        this.nota = nota;
    }

    public Feedback(String comentariu, int nota, String user) {
        this.comentariu = comentariu;
        this.nota = nota;
        this.user = user;
    }

    protected Feedback(Parcel in) {
        comentariu = in.readString();
        nota = in.readInt();
    }

    public static final Creator<Feedback> CREATOR = new Creator<Feedback>() {
        @Override
        public Feedback createFromParcel(Parcel parcel) {
            return new Feedback(parcel);
        }

        @Override
        public Feedback[] newArray(int i) {
            return new Feedback[0];
        }
    };

    public String getComentariu() {
        return comentariu;
    }

    public void setComentariu(String comentariu) {
        this.comentariu = comentariu;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(comentariu);
        dest.writeInt(nota);
    }
}
