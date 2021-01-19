package com.geekbrains.androidbasiclevel.pospelov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements OnClickListener {

//    public static String theme = "LIGHT";
//    TextView textViewCity;
//    TextView textViewWindSpeedToday;
//    TextView textViewAirPressureToday;
//    TextView airPressureTodayView;
//    TextView windSpeedTodayView;
//    TextView textViewWindSpeedTomorrow;
//    TextView windSpeedTomorrowView;
//    TextView textViewAirPressureTomorrow;
//    TextView airPressureTomorrowView;
//    TextView textTempToday;
//    TextView textTempTomorrow;
//    MainPresenter presenter = MainPresenter.getInstance();

    private final int settingsActivityRequestCode = 1234;

 //   @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        switch (theme) {
//            case "DARK":
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                break;
//            case "LIGHT":
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                break;
//        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        TextView textTodayDate = findViewById(R.id.textTodayDateView);
        Calendar today = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("E, dd MMM");
        textTodayDate.setText(df.format(today.getTime()));
        TextView textTomorrowDate = findViewById(R.id.textTomorrowDateView);
        today.roll(Calendar.DAY_OF_MONTH, +1);
        textTomorrowDate.setText(df.format(today.getTime()));

        ImageButton btnSettings;
        btnSettings = findViewById(R.id.buttonSettings);
        btnSettings.setOnClickListener(this);
        ImageButton btnInfo;
        btnInfo = findViewById(R.id.imageButtonInfo);
        btnInfo.setOnClickListener(this);

        textViewCity = findViewById(R.id.cityView);
        textViewWindSpeedToday =  findViewById(R.id.textViewWindSpeedToday);
        windSpeedTodayView =  findViewById(R.id.windSpeedTodayView);
        textViewAirPressureToday = findViewById(R.id.textViewAirPressureToday);
        airPressureTodayView = findViewById(R.id.airPressureTodayView);
        textViewWindSpeedTomorrow = findViewById(R.id.textViewWindSpeedTomorrow);
        windSpeedTomorrowView = findViewById(R.id.windSpeedTomorrowView);
        textViewAirPressureTomorrow = findViewById(R.id.textViewAirPressureTomorrow);
        airPressureTomorrowView = findViewById(R.id.airPressureTomorrowView);
        textTempToday = findViewById(R.id.textTempTodayView);
        textTempTomorrow = findViewById(R.id.textTempTomorrowView);

        textTempToday.setText(presenter.getDegreesToday() + " °C");
        textTempTomorrow.setText(presenter.getDegreesTomorrow() + " °C");

        if (presenter.getCityName() != null){
            textViewCity.setText(presenter.getCityName());
        } else {
            String[] citys = getResources().getStringArray(R.array.city_array);
            textViewCity.setText(citys[0]);
        }

 */
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int countOfFragmentInManager = getSupportFragmentManager().getBackStackEntryCount();
        if(countOfFragmentInManager > 0) {
            getSupportFragmentManager().popBackStack();
            /*getSupportFragmentManager().popBackStack("Some_Key",
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);*/
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSettings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.imageButtonInfo:                                         //открытие браузера с поиском информации по городу в Яндексе
                final TextView textViewCity = findViewById(R.id.cityView);
                String url = "https://yandex.ru/search/?text=" + textViewCity.getText().toString();
                Uri uri = Uri.parse(url);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
                break;


            default:
                break;
        }
    }




}