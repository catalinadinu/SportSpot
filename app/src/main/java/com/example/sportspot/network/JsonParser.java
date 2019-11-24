package com.example.sportspot.network;

import com.example.sportspot.util.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class JsonParser {

    public static HttpResponse parseJson(String json){
        if (json == null) {
            return null;
        }

        try{
            JSONObject jsonObject = new JSONObject(json);
            List<Game> fotbal = getGameListFromJson(jsonObject.getJSONArray("fotbal"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Game> getGameListFromJson(JSONArray jsonArray) throws JSONException {
        if (jsonArray == null) {
            return null;
        }
        List<Game> results = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Game game = getGameFromJson(jsonArray.getJSONObject(i));
            if (game != null) {
                results.add(game);
            }
        }
        return results;
    }

    private static Game getGameFromJson(JSONObject jsonObject) throws JSONException {
        if(jsonObject == null) {
            return null;
        }
        String meci = jsonObject.getString("meci");
        String scor = jsonObject.getString("scor");
        ExtraInfo extraInfo = getExtraInfoFromJson(jsonObject.getJSONObject("extraInfo"));
        return new Game(meci, scor, extraInfo);
    }

    private static ExtraInfo getExtraInfoFromJson(JSONObject jsonObject) throws JSONException {
        if(jsonObject == null){
            return null;
        }

        Date data = null;
        try {
            data = new SimpleDateFormat(Const.DATE_FORMAT, Locale.US).parse(jsonObject.getString("data"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String locatie = jsonObject.getString("locatie");

        int cartonaseRosii = jsonObject.getInt("cartonaseRosii");

        return  new ExtraInfo(data, locatie, cartonaseRosii);
    }
}
