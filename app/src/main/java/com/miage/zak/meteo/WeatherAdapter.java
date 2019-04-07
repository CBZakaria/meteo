package com.miage.zak.meteo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miage.zak.meteo.data.FcstDay;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;


public class WeatherAdapter extends ArrayAdapter<FcstDay> {

    private final Context mContext;
    private final ArrayList<FcstDay> mWeatherDays;


    public WeatherAdapter(Context context, int resource) {
        super(context, resource);
        mContext = context;
        mWeatherDays = new ArrayList<FcstDay>();
    }

    @Override
    public int getPosition(FcstDay item) {
        return mWeatherDays.indexOf(item);
    }

    @Override
    public void addAll(Collection<? extends FcstDay> items) {
        mWeatherDays.clear();
        mWeatherDays.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mWeatherDays.size();
    }


    @Override
    public FcstDay getItem(int position) {
        return mWeatherDays.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.weather_list_item, parent,false);
        ImageView imageView = rowView.findViewById(R.id.weather_ic);
        TextView weaterData = rowView.findViewById(R.id.tv_weather_data);
        TextView weatherDate = rowView.findViewById(R.id.tv_weather_date);
        TextView mTminTmax = rowView.findViewById(R.id.tmin_max_tV);

        FcstDay dayToShow = getItem(position);
        weaterData.setText(dayToShow.getDay_long());
        weatherDate.setText(dayToShow.getDate());
        mTminTmax.setText(dayToShow.getTmax()+ "° / "+dayToShow.getTmin()+"°");
        Picasso.get().load(dayToShow.getIcon_big()).into(imageView);
        return rowView;
    }
}
