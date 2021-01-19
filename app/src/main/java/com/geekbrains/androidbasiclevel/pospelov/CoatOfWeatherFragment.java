package com.geekbrains.androidbasiclevel.pospelov;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class CoatOfWeatherFragment extends Fragment {
    TextView textViewCity;
    TextView textViewWindSpeedToday;
    TextView textViewAirPressureToday;
    TextView airPressureTodayView;
    TextView windSpeedTodayView;
    TextView textViewWindSpeedTomorrow;
    TextView windSpeedTomorrowView;
    TextView textViewAirPressureTomorrow;
    TextView airPressureTomorrowView;
    TextView textTempToday;
    TextView textTempTomorrow;
    MainPresenter presenter = MainPresenter.getInstance();

    static CoatOfWeatherFragment create(CoatContainer container) {
        CoatOfWeatherFragment fragment = new CoatOfWeatherFragment();    // создание


        // Передача параметра
        Bundle args = new Bundle();
        args.putSerializable("index", container);

        fragment.setArguments(args);
        return fragment;
    }

    // Получить индекс из списка (фактически из параметра)
    int getIndex() {
        CoatContainer coatContainer = (CoatContainer) (getArguments()).getSerializable("index");

        try {
            return coatContainer.position;
        } catch (Exception e) {
            return 0;
        }
    }

    String getCityName() {
        CoatContainer coatContainer = (CoatContainer) (Objects.requireNonNull(getArguments()).getSerializable("index"));

        try {
            return coatContainer.cityName;
        } catch (Exception e) {
            return "";
        }
    }



    @Override
    @SuppressLint("Recycle")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_coat_of_weather, container, false);
        textViewCity = layout.findViewById(R.id.cityView);

        String cityName = getCityName();
        textViewCity.setText(cityName);

        TextView textTodayDate = layout.findViewById(R.id.textTodayDateView);
        Calendar today = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("E, dd MMM");
        textTodayDate.setText(df.format(today.getTime()));
        TextView textTomorrowDate = layout.findViewById(R.id.textTomorrowDateView);
        today.roll(Calendar.DAY_OF_MONTH, +1);
        textTomorrowDate.setText(df.format(today.getTime()));

        textViewWindSpeedToday =  layout.findViewById(R.id.textViewWindSpeedToday);
        windSpeedTodayView =  layout.findViewById(R.id.windSpeedTodayView);
        textViewAirPressureToday = layout.findViewById(R.id.textViewAirPressureToday);
        airPressureTodayView = layout.findViewById(R.id.airPressureTodayView);
        textViewWindSpeedTomorrow = layout.findViewById(R.id.textViewWindSpeedTomorrow);
        windSpeedTomorrowView = layout.findViewById(R.id.windSpeedTomorrowView);
        textViewAirPressureTomorrow = layout.findViewById(R.id.textViewAirPressureTomorrow);
        airPressureTomorrowView = layout.findViewById(R.id.airPressureTomorrowView);
        textTempToday = layout.findViewById(R.id.textTempTodayView);
        textTempTomorrow = layout.findViewById(R.id.textTempTomorrowView);

        textTempToday.setText(presenter.getDegreesToday() + " °C");
        textTempTomorrow.setText(presenter.getDegreesTomorrow() + " °C");

        if (presenter.isCheckWindSpeed()){
            textViewWindSpeedToday.setVisibility(View.VISIBLE);
            windSpeedTodayView.setVisibility(View.VISIBLE);
            textViewWindSpeedTomorrow.setVisibility(View.VISIBLE);
            windSpeedTomorrowView.setVisibility(View.VISIBLE);
        } else {
            textViewWindSpeedToday.setVisibility(View.GONE);
            windSpeedTodayView.setVisibility(View.GONE);
            textViewWindSpeedTomorrow.setVisibility(View.GONE);
            windSpeedTomorrowView.setVisibility(View.GONE);
        }
        if (presenter.isCheckAtmosphericPressure()){
            textViewAirPressureToday.setVisibility(View.VISIBLE);
            airPressureTodayView.setVisibility(View.VISIBLE);
            textViewAirPressureTomorrow.setVisibility(View.VISIBLE);
            airPressureTomorrowView.setVisibility(View.VISIBLE);
        } else {
            textViewAirPressureToday.setVisibility(View.GONE);
            airPressureTodayView.setVisibility(View.GONE);
            textViewAirPressureTomorrow.setVisibility(View.GONE);
            airPressureTomorrowView.setVisibility(View.GONE);
        }
//        ImageButton btnInfo;
//        btnInfo = layout.findViewById(R.id.imageButtonInfo);
//        btnInfo.setOnClickListener(this);

//        LinearLayout layout = new LinearLayout(getContext());
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.removeAllViews();

//        String cityName = getCityName();
//        TextView cityNameTextView = new TextView(getContext());
//        cityNameTextView.setText(cityName);
//        layout.addView(cityNameTextView);

//        TextView textViewCity = container.findViewById(R.id.cityView);
//        textViewCity.setText("DSDASDSDASDASD");



//        TextView textViewCity = layout.findViewById(R.id.cityView);
//        textViewCity.setText("DSDASDSDASDASD");
//        TextView textViewCity = layout.findViewById(R.id.cityView);

////        textViewCity.setText("DSDASDSDASDASD");


//        // Определить какой герб надо показать, и показать его
//        ImageView coatOfArms = new ImageView(getActivity());
//
//        // Получить из ресурсов массив указателей на изображения гербов
//        TypedArray images = getResources().obtainTypedArray(R.array.coatofarms_imgs);
//        // Выбрать по индексу подходящий
//        coatOfArms.setImageResource(images.getResourceId(getIndex(), -1));

//        TextView test = new TextView(getActivity());
//        TypedArray i = getResources().obtainTypedArray(R.array.weather_array);
//        test.setText(i.getResourceId(getIndex(), -1));

        //layout.addView(test);
//        layout.addView(coatOfArms);

        return layout;
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.imageButtonInfo:                                         //открытие браузера с поиском информации по городу в Яндексе
//                final TextView textViewCity = findViewById(R.id.cityView);
//                String url = "https://yandex.ru/search/?text=" + textViewCity.getText().toString();
//                Uri uri = Uri.parse(url);
//                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(browser);
//                break;
//
//
//            default:
//                break;
//        }
//    }

}
