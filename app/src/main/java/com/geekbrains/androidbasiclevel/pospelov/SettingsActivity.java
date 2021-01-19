package com.geekbrains.androidbasiclevel.pospelov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
//    private AutoCompleteTextView textView;
    private CheckBox checkWindSpeed;
    private CheckBox checkAtmosphericPressure;
    final MainPresenter presenter = MainPresenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
//        textView = (AutoCompleteTextView) findViewById(R.id.textChangeCityView);
//        String[] countries = getResources().getStringArray(R.array.city_array);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
//        textView.setAdapter(adapter);

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
//        Button btnTheme;
//        btnTheme = (Button) findViewById(R.id.buttonDarkOrLight);
//        if (MainActivity.theme.equals("LIGHT")){
//            btnTheme.setText(getString(R.string.buttonDarkText));
//        } else if (MainActivity.theme.equals("DARK")){
//            btnTheme.setText(getString(R.string.buttonLightText));
//        }
//        btnTheme.setOnClickListener(this);
    }

    @SuppressLint({"NonConstantResourceId", "WrongConstant"})
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonSelect:
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
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

//                Intent intentMainActivity = new Intent();
 //               String cityName = this.textView.getText().toString();       //передача введенного пользователем города в MainActivity
//                intentMainActivity.putExtra("cityName", cityName);
//                setResult(RESULT_OK, intentMainActivity);
//                presenter.setDegreesToday(44);
//                Intent intent = new Intent(this, CoatOfWeatherActivity.class);
//                startActivity(intent);



                finish();
                break;

            default:
                break;
        }
    }

}