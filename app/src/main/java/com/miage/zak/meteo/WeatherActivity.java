package com.miage.zak.meteo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;


import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.miage.zak.meteo.data.DonneesMeteo;
import com.miage.zak.meteo.data.FcstDay;


import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    private ListView mWeatherListV;
    private SwipeRefreshLayout mRefresher;
    private List<FcstDay> mWeatherDays = new ArrayList<FcstDay>();
    private ArrayAdapter<FcstDay> mWeatherAdapter;
    private TextView mWeatherCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        mWeatherCity = findViewById(R.id.weather_city);
        mWeatherListV = findViewById(R.id.weather_listV);
        mRefresher = findViewById(R.id.swipe_Refresh);
        mWeatherAdapter = new WeatherAdapter(this, 0);
        mWeatherListV.setAdapter(mWeatherAdapter);
        mRefresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFromInternet();
            }
        });
        mRefresher.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshFromInternet();
            }
        }, 0);
        mWeatherListV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FcstDay chosenDay = mWeatherDays.get(position);
                Intent intent = new Intent(WeatherActivity.this, DailyActivity.class);
                intent.putExtra("chosenDay", chosenDay.toJsonString());
                intent.putExtra("CITY", mWeatherCity.getText().toString());
                startActivity(intent);
            }
        });


    }

    private class FetchWeatheerTask extends AsyncTask<String, Void, DonneesMeteo> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected DonneesMeteo doInBackground(String... urls) {
            callWebService();
            return null;
        }

        @Override
        protected void onPostExecute(DonneesMeteo donneesMeteo) {
            super.onPostExecute(donneesMeteo);
        }


        private void callWebService() {
            String url = "https://www.prevision-meteo.ch/services/json/grenoble";
            RequestQueue queue = Volley.newRequestQueue(WeatherActivity.this);

            StringRequest request = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        DonneesMeteo donneesMeteo;

                        @Override
                        public void onResponse(String response) {
                            donneesMeteo = new DonneesMeteo(response);
                            mWeatherCity.setText(donneesMeteo.getCityInfo().getName());
                            mWeatherDays.clear();
                            mWeatherDays.add(donneesMeteo.getFcstDay0());
                            mWeatherDays.add(donneesMeteo.getFcstDay1());
                            mWeatherDays.add(donneesMeteo.getFcstDay2());
                            mWeatherDays.add(donneesMeteo.getFcstDay3());
                            mWeatherDays.add(donneesMeteo.getFcstDay4());

                            mWeatherAdapter.addAll(mWeatherDays);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            queue.add(request);
        }
    }

    private void refreshFromInternet() {
        mRefresher.setRefreshing(true);
        new FetchWeatheerTask().execute();
        mRefresher.setRefreshing(false);
    }
    private void updateWithNewLocation(Location location) {
        TextView myLocationText = (TextView) findViewById(R.id.text);
        String latLongString = "";
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "Lat:" + lat + "\nLong:" + lng;
        } else {
            latLongString = "No location found";
        }
        myLocationText.setText("Your Current Position is:\n" + latLongString);
    }

    private final LocationListener locationListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        public void onProviderDisabled(String provider) {
            updateWithNewLocation(null);
        }

        public void onProviderEnabled(String provider) {}

        public void onStatusChanged(String provider,int status,Bundle extras){}
    };
}
