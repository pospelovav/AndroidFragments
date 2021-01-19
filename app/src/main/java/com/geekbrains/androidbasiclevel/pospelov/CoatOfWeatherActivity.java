package com.geekbrains.androidbasiclevel.pospelov;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;

public class CoatOfWeatherActivity extends AppCompatActivity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //TextView textViewCity = findViewById(R.id.cityView);
//        ImageButton btnSettings;
//        btnSettings = findViewById(R.id.buttonSettings);
//        btnSettings.setOnClickListener(this);
        ImageButton btnInfo;
        btnInfo = findViewById(R.id.imageButtonInfo);
        btnInfo.setOnClickListener(this);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Если устройство перевернули в альбомную ориентацию, то надо эту activity закрыть
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // Если эта activity запускается первый раз (с каждым новым гербом первый раз)
            // то перенаправим параметр фрагменту
            CoatOfWeatherFragment details = new CoatOfWeatherFragment();
            details.setArguments(getIntent().getExtras());
            // Добавим фрагмент на activity
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, details)
                    .commit();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.buttonSettings:
//                Intent intent = new Intent(this, SettingsActivity.class);
//                startActivity(intent);
//                break;
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
