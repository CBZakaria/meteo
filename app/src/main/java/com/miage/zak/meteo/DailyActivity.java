package com.miage.zak.meteo;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.miage.zak.meteo.data.FcstDay;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DailyActivity extends Activity {

    private FcstDay dayDetails;
    private TextView mCity;
    private ImageView tIconWeather;
    private TextView mTemperature;
    private TextView mTminTmax;
    private TextView mConditiony;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        mCity = findViewById(R.id.city_name);
        tIconWeather = findViewById(R.id.weather_icon);
        mTemperature = findViewById(R.id.temperature_textV);
        mTminTmax = findViewById(R.id.tmin_max);
        mConditiony = findViewById(R.id.condition_tv);

        Intent intent = getIntent();
        try {
            dayDetails = new FcstDay(new JSONObject(intent.getStringExtra("chosenDay")));
            mCity.setText(dayDetails.getDay_long());
            mTemperature.setText(dayDetails.getCondition());
            mTminTmax.setText(dayDetails.getTmax()+" / " +dayDetails.getTmin());
            mConditiony.setText(dayDetails.getCondition_key());
            Picasso.get().load(dayDetails.getIcon_big()).into(tIconWeather);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
