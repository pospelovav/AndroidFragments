package com.geekbrains.androidbasiclevel.pospelov;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textTempToday = findViewById(R.id.textTempTodayView);
        textTempToday.setText("-4 °C");
        TextView textTempTomorrow = findViewById(R.id.textTempTomorrowView);
        textTempTomorrow.setText("0 °C");
        TextView textTodayDate = findViewById(R.id.textTodayDateView);
        Calendar today = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("E, dd MMM yyy");
        textTodayDate.setText(df.format(today.getTime()));
        TextView textTomorrowDate = findViewById(R.id.textTomorrowDateView);
        today.roll(Calendar.DAY_OF_MONTH, +1);
        textTomorrowDate.setText(df.format(today.getTime()));

        Button btnSettings;
        btnSettings = (Button) findViewById(R.id.buttonSettings);
        btnSettings.setOnClickListener(this);

        String[] citys = getResources().getStringArray(R.array.city_array);
        TextView textViewCity = findViewById(R.id.cityView);
        textViewCity.setText(citys[2]);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSettings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}