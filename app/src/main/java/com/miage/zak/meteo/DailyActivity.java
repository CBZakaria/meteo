package com.miage.zak.meteo;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.miage.zak.meteo.data.FcstDay;
import com.miage.zak.meteo.data.Hour;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class DailyActivity extends Activity {

    private FcstDay dayDetails;
    private Hour hour;
    private TextView mCity;
    private TextView mDateDetails;
    private ImageView tIconWeather;
    private TextView mTemperature;
    private TextView mTminTmax;
    private TextView mConditiony;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        mCity = findViewById(R.id.city_name);
        mDateDetails = findViewById(R.id.date_details);
        tIconWeather = findViewById(R.id.weather_icon);
        mTemperature = findViewById(R.id.temperature_textV);
        mTminTmax = findViewById(R.id.tmin_max);
        mConditiony = findViewById(R.id.condition_tv);

        Intent intent = getIntent();
        try {
            dayDetails = new FcstDay(new JSONObject(intent.getStringExtra("chosenDay")));
            hour = dayDetails.getHourlyData().get(getCurrentHour());
            mCity.setText(intent.getStringExtra("CITY"));
            mDateDetails.setText(getCurrentHour()+"H" +new Date().getMinutes()+" "+ dayDetails.getDate());
            mTemperature.setText(hour.getTMP2m()+"°");
            mTminTmax.setText(dayDetails.getTmax()+"° / " +dayDetails.getTmin()+"°");
            mConditiony.setText(hour.getCONDITION());
            Picasso.get().load(hour.getICON()).into(tIconWeather);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private int getCurrentHour(){
        return new Date().getHours();
    }
}
