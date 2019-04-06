package com.miage.zak.meteo.data;


import org.json.JSONException;
import org.json.JSONObject;

public class ForecastInfo {

    private String latitude;
    private String longitude;
    private String elevation;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }



    public void setElevation(String elevation) {
        this.elevation = elevation;
    }


    public String getElevation() {
        return elevation;
    }

    public ForecastInfo(JSONObject object) throws JSONException {
        latitude = object.getString("latitude");
        longitude = object.getString("longitude");
        elevation = object.getString("elevation");
    }
}
