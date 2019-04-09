package com.miage.zak.meteo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.miage.zak.meteo.data.DonneesMeteo;
import com.miage.zak.meteo.data.FcstDay;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class WeatherActivity extends AppCompatActivity {

    private ListView mWeatherListV;
    private SwipeRefreshLayout mRefresher;
    private List<FcstDay> mWeatherDays = new ArrayList<FcstDay>();
    private ArrayAdapter<FcstDay> mWeatherAdapter;
    private TextView mWeatherCity;
    private FusedLocationProviderClient fusedLocationClient;
    private String weatherURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        requestPermission();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
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

            RequestQueue queue = Volley.newRequestQueue(WeatherActivity.this);
            getLocation();
            StringRequest request = new StringRequest(Request.Method.GET, weatherURL,
                    new Response.Listener<String>() {
                        DonneesMeteo donneesMeteo;

                        @Override
                        public void onResponse(String response) {
                            donneesMeteo = new DonneesMeteo(response);
                            addWeatherInfo(donneesMeteo);
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
    private void requestPermission () {
        ActivityCompat.requestPermissions(this, new String[] {ACCESS_FINE_LOCATION},1);
    }

    private void refreshFromInternet() {
        mRefresher.setRefreshing(true);
        new FetchWeatheerTask().execute();
        mRefresher.setRefreshing(false);

    }

    private void getLocation () {
        if (ActivityCompat.checkSelfPermission(WeatherActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(WeatherActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mWeatherCity.setText("Grenoble");
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(WeatherActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        String url = "https://www.prevision-meteo.ch/services/json/grenoble";
                        if (location != null) {
                            double longitude = location.getLongitude();
                            double latitude = location.getLatitude();
                            url = "https://www.prevision-meteo.ch/services/json/lat="+latitude+"lng="+longitude;
                            Geocoder gcd = new Geocoder(WeatherActivity.this, Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = gcd.getFromLocation(latitude, longitude, 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (addresses.size() > 0) {
                                String cityName = addresses.get(0).getLocality();
                                mWeatherCity.setText(cityName);
                            }
                        }
                        weatherURL = url;
                    }
                });
    }

    private void addWeatherInfo (DonneesMeteo donneesMeteo) {
        mWeatherDays.clear();
        mWeatherDays.add(donneesMeteo.getFcstDay0());
        mWeatherDays.add(donneesMeteo.getFcstDay1());
        mWeatherDays.add(donneesMeteo.getFcstDay2());
        mWeatherDays.add(donneesMeteo.getFcstDay3());
        mWeatherDays.add(donneesMeteo.getFcstDay4());
        mWeatherAdapter.addAll(mWeatherDays);

    }

}
