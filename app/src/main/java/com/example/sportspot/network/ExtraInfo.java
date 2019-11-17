package com.example.sportspot.network;

public class ExtraInfo {
    private String data;
    private String locatie;
    private int cartonaseRosii;

    public ExtraInfo(String data, String locatie, int cartonaseRosii) {
        this.data = data;
        this.locatie = locatie;
        this.cartonaseRosii = cartonaseRosii;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public int getCartonaseRosii() {
        return cartonaseRosii;
    }

    public void setCartonaseRosii(int cartonaseRosii) {
        this.cartonaseRosii = cartonaseRosii;
    }

    @Override
    public String toString() {
        return "Data meciului:" + data + ", \n" +
                "locatia meciului: " + locatie + ", \n" +
                "numarul de cartonase rosii acordate in timpul meciului: " + cartonaseRosii;
    }
}
