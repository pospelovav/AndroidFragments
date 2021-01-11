package com.geekbrains.androidbasiclevel.pospelov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private AutoCompleteTextView textView;
    private CheckBox checkWindSpeed;
    private CheckBox checkAtmosphericPressure;
    private static final String TAG = "SETTINGS_ACTIVITY";
    final MainPresenter presenter = MainPresenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        textView = (AutoCompleteTextView) findViewById(R.id.textChangeCityView);
        String[] countries = getResources().getStringArray(R.array.city_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);
        textView.setText(presenter.getCityName());
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intentMainActivity = new Intent(this, MainActivity.class);
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
                presenter.setCityName(this.textView.getText().toString());
                startActivity(intentMainActivity);
                break;
            case R.id.buttonDarkOrLight:
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