package com.example.sportspot.network;

import java.util.Date;

public class ExtraInfo {
    private Date data;
    private String locatie;
    private Integer cartonaseRosii;

    public ExtraInfo(Date data, String locatie, int cartonaseRosii) {
        this.data = data;
        this.locatie = locatie;
        this.cartonaseRosii = cartonaseRosii;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public Integer getCartonaseRosii() {
        return cartonaseRosii;
    }

    public void setCartonaseRosii(Integer cartonaseRosii) {
        this.cartonaseRosii = cartonaseRosii;
    }

    @Override
    public String toString() {
        return "Data meciului:" + data + ", \n" +
                "locatia meciului: " + locatie + ", \n" +
                "numarul de cartonase rosii acordate in timpul meciului: " + cartonaseRosii;
    }
}
