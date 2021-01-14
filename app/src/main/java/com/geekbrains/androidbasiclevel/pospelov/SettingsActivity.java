package com.geekbrains.androidbasiclevel.pospelov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private AutoCompleteTextView textView;
    private CheckBox checkWindSpeed;
    private CheckBox checkAtmosphericPressure;
    final MainPresenter presenter = MainPresenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        textView = (AutoCompleteTextView) findViewById(R.id.textChangeCityView);
        String[] countries = getResources().getStringArray(R.array.city_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);

        Button btnSelect;
        btnSelect = (Button) findViewById(R.id.buttonSelect);
        btnSelect.setOnClickListener(this);
        checkAtmosphericPressure = (CheckBox) findViewById(R.id.checkBoxAtmosphericPressure);
        checkWindSpeed = (CheckBox) findViewById(R.id.checkBoxWindSpeed);
        if (presenter.isCheckWindSpeed()){
            checkWindSpeed.setChecked(true);
        }
        if (presenter.isCheckAtmosphericPressure()){
            checkAtmosphericPressure.setChecked(true);
        }
        Button btnTheme;
        btnTheme = (Button) findViewById(R.id.buttonDarkOrLight);
        if (MainActivity.theme.equals("LIGHT")){
            btnTheme.setText(getString(R.string.buttonDarkText));
        } else if (MainActivity.theme.equals("DARK")){
            btnTheme.setText(getString(R.string.buttonLightText));
        }
        btnTheme.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (this.checkWindSpeed.isChecked()){
            presenter.setCheckWindSpeed(true);
        } else {
            presenter.setCheckWindSpeed(false);
        }
        if (this.checkAtmosphericPressure.isChecked()){
            presenter.setCheckAtmosphericPressure(true);
        } else {
            presenter.setCheckAtmosphericPressure(false);
        }
        switch (v.getId()) {
            case R.id.buttonSelect:
                Intent intentMainActivity = new Intent();
                String cityName = this.textView.getText().toString();       //передача введенного пользователем города в MainActivity
                intentMainActivity.putExtra("cityName", cityName);
                setResult(RESULT_OK, intentMainActivity);
                finish();
                break;
            case R.id.buttonDarkOrLight:                    //смена темы приложения
                if (MainActivity.theme.equals("DARK")){
                    MainActivity.theme = "LIGHT";
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                } else if (MainActivity.theme.equals("LIGHT")){
                    MainActivity.theme = "DARK";
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                }
                break;
            default:
                break;
        }
    }

}