package com.miage.zak.meteo.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FcstDay {
    private String date;
    private String day_short;
    private String day_long;
    private float tmin;
    private float tmax;
    private String condition;
    private String condition_key;
    private String icon;
    private String icon_big;

    public Object getHourlyData() {
        return hourlyData;
    }

    public void setHourlyData(Object hourlyData) {
        this.hourlyData = hourlyData;
    }

    private Object hourlyData;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay_short() {
        return day_short;
    }

    public void setDay_short(String day_short) {
        this.day_short = day_short;
    }

    public String getDay_long() {
        return day_long;
    }

    public void setDay_long(String day_long) {
        this.day_long = day_long;
    }

    public float getTmin() {
        return tmin;
    }

    public void setTmin(float tmin) {
        this.tmin = tmin;
    }

    public float getTmax() {
        return tmax;
    }

    public void setTmax(float tmax) {
        this.tmax = tmax;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition_key() {
        return condition_key;
    }

    public void setCondition_key(String condition_key) {
        this.condition_key = condition_key;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon_big() {
        return icon_big;
    }

    public void setIcon_big(String icon_big) {
        this.icon_big = icon_big;
    }

    private String m_sJonString;

    public FcstDay(JSONObject object) throws JSONException {

        m_sJonString = object.toString();

        date = object.getString("date");
        day_long= object.getString("day_long");
        day_short= object.getString("day_short");
        tmin= object.getLong("tmin");
        tmax= object.getLong("tmax");
        condition= object.getString("condition");
        condition_key= object.getString("condition_key");
        icon= object.getString("icon");
        hourlyData = object.get("hourly_data");
        icon_big= object.getString("icon_big");
    }
    public String toJsonString(){
        return m_sJonString;
    }
}
