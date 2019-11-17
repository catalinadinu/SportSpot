package com.example.sportspot.network;

import java.util.List;

public class HttpResponse {
    private List<Game> fotbal;
    private List<Game> handbal;
    private List<Game> volei;

    public HttpResponse(List<Game> fotbal, List<Game> handbal, List<Game> volei) {
        this.fotbal = fotbal;
        this.handbal = handbal;
        this.volei = volei;
    }

    public List<Game> getFotbal() {
        return fotbal;
    }

    public void setFotbal(List<Game> fotbal) {
        this.fotbal = fotbal;
    }

    public List<Game> getHandbal() {
        return handbal;
    }

    public void setHandbal(List<Game> handbal) {
        this.handbal = handbal;
    }

    public List<Game> getVolei() {
        return volei;
    }

    public void setVolei(List<Game> volei) {
        this.volei = volei;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "fotbal=" + fotbal +
                ", handbal=" + handbal +
                ", volei=" + volei +
                '}';
    }
}
