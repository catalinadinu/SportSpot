package com.example.sportspot.network;

public class Game {
    private String meci;
    private String scor;
    private ExtraInfo extraInfo;

    public Game(String meci, String scor, ExtraInfo extraInfo) {
        this.meci = meci;
        this.scor = scor;
        this.extraInfo = extraInfo;
    }

    public String getMeci() {
        return meci;
    }

    public void setMeci(String meci) {
        this.meci = meci;
    }

    public String getScor() {
        return scor;
    }

    public void setScor(String scor) {
        this.scor = scor;
    }

    public ExtraInfo getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(ExtraInfo extraInfo) {
        this.extraInfo = extraInfo;
    }

    @Override
    public String toString() {
        return "Game{" +
                "meci='" + meci + '\'' +
                ", scor='" + scor + '\'' +
                ", extraInfo=" + extraInfo +
                '}';
    }
}
