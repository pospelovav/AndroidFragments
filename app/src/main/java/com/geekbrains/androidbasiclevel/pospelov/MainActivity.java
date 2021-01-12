package com.geekbrains.androidbasiclevel.pospelov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    public static String theme = "LIGHT";
    private static final String TAG = "MAIN_ACTIVITY";
    TextView textViewCity;
    private final int settingsActivityRequestCode = 1234;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        switch (theme) {
            case "DARK":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case "LIGHT":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textTodayDate = findViewById(R.id.textTodayDateView);
        Calendar today = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("E, dd MMM");
        textTodayDate.setText(df.format(today.getTime()));
        TextView textTomorrowDate = findViewById(R.id.textTomorrowDateView);
        today.roll(Calendar.DAY_OF_MONTH, +1);
        textTomorrowDate.setText(df.format(today.getTime()));

        ImageButton btnSettings;
        btnSettings = (ImageButton) findViewById(R.id.buttonSettings);
        btnSettings.setOnClickListener(this);
        ImageButton btnInfo;
        btnInfo = (ImageButton) findViewById(R.id.imageButtonInfo);
        btnInfo.setOnClickListener(this);

        textViewCity = (TextView) findViewById(R.id.cityView);
        final TextView textViewWindSpeedToday = findViewById(R.id.textViewWindSpeedToday);
        final TextView windSpeedTodayView = findViewById(R.id.windSpeedTodayView);
        final TextView textViewAirPressureToday = findViewById(R.id.textViewAirPressureToday);
        final TextView airPressureTodayView = findViewById(R.id.airPressureTodayView);
        final TextView textViewWindSpeedTomorrow = findViewById(R.id.textViewWindSpeedTomorrow);
        final TextView windSpeedTomorrowView = findViewById(R.id.windSpeedTomorrowView);
        final TextView textViewAirPressureTomorrow = findViewById(R.id.textViewAirPressureTomorrow);
        final TextView airPressureTomorrowView = findViewById(R.id.airPressureTomorrowView);
        final TextView textTempToday = findViewById(R.id.textTempTodayView);
        final TextView textTempTomorrow = findViewById(R.id.textTempTomorrowView);
        final MainPresenter presenter = MainPresenter.getInstance();

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
        if (presenter.getCityName() != null){
            textViewCity.setText(presenter.getCityName());
        } else {
            String[] citys = getResources().getStringArray(R.array.city_array);
            textViewCity.setText(citys[0]);
        }

        String instanceState;
        if (savedInstanceState == null){
            instanceState = "Fist start " + TAG;
        }
        else{
            instanceState = "Restart " + TAG;
        }
        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_LONG).show();
        Log.d(TAG, instanceState + " - onCreate()");

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSettings:
                Intent intentSettingsActivity = new Intent(this, SettingsActivity.class);    //вызов SettingsActivity с ожиданием результата
                startActivityForResult(intentSettingsActivity, settingsActivityRequestCode);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent dataFromSettingsActivity) {   //обработка результата из SettingsActivity
        if(requestCode == settingsActivityRequestCode && resultCode == RESULT_OK) {
            String cityNameFromSettings;
            if (dataFromSettingsActivity != null) {
                cityNameFromSettings = dataFromSettingsActivity.getStringExtra("cityName");
                if (!cityNameFromSettings.equals("")) {  //если пользователь не ввел город
                    textViewCity.setText(cityNameFromSettings);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, dataFromSettingsActivity);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getApplicationContext(), TAG + " onRestoreInstanceState()", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onRestoreInstanceState()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), TAG + " onStop()", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), TAG + " onResume()", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), TAG + " onPause()", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(getApplicationContext(), TAG + " onSaveInstanceState()", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onSaveInstanceState()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), TAG + " onRestart()", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), TAG + " onDestroy()", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy()");
    }

}