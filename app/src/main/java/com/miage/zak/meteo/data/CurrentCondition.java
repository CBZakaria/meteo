package com.miage.zak.meteo.data;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrentCondition {
    private String date;
    private String hour;
    private float tmp;
    private float wnd_spd;
    private float wnd_gust;
    private String wnd_dir;
    private float pressure;
    private float humidity;
    private String condition;
    private String condition_key;
    private String icon;
    private String icon_big;


    // Getter Methods

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public float getTmp() {
        return tmp;
    }

    public float getWnd_spd() {
        return wnd_spd;
    }

    public float getWnd_gust() {
        return wnd_gust;
    }

    public String getWnd_dir() {
        return wnd_dir;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public String getCondition() {
        return condition;
    }

    public String getCondition_key() {
        return condition_key;
    }

    public String getIcon() {
        return icon;
    }

    public String getIcon_big() {
        return icon_big;
    }

    // Setter Methods

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setTmp(float tmp) {
        this.tmp = tmp;
    }

    public void setWnd_spd(float wnd_spd) {
        this.wnd_spd = wnd_spd;
    }

    public void setWnd_gust(float wnd_gust) {
        this.wnd_gust = wnd_gust;
    }

    public void setWnd_dir(String wnd_dir) {
        this.wnd_dir = wnd_dir;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setCondition_key(String condition_key) {
        this.condition_key = condition_key;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setIcon_big(String icon_big) {
        this.icon_big = icon_big;
    }
    public CurrentCondition(JSONObject object) throws JSONException {
        String date = object.getString("date");
        String hour = object.getString("hour");
        float tmp = object.getLong("tmp");
        float wnd_spd = object.getLong("wnd_spd");
        float wnd_gust = object.getLong("wnd_gust");
        String wnd_dir = object.getString("wnd_dir");
        float pressure = object.getLong("pressure");
        float humidity = object.getLong("humidity");
        String condition = object.getString("condition");
        String condition_key = object.getString("condition_key");
        String icon = object.getString("icon");
        String icon_big = object.getString("icon_big");
    }
}
