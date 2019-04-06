package com.miage.zak.meteo.data;

import org.json.JSONException;
import org.json.JSONObject;

public class HourlyData {

    private Hour[] houlyData = new Hour[24];

    public Hour[] getHoulyData() {
        return houlyData;
    }

    public void setHoulyData(Hour[] houlyData) {
        this.houlyData = houlyData;
    }

    public Hour get(int i) {
        return houlyData[i];
    }

   public HourlyData(JSONObject object) throws JSONException {

        for(int i = 0; i <24; i++) {
            houlyData[i] = new Hour(object.getJSONObject(i+"H00"));
        }
   }
}
