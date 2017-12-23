package com.example.oliver.restorani;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {
    @BindView(R.id.editText1)
    EditText name;
    @BindView(R.id.editText2)
    EditText lokacija;
    @BindView(R.id.editText3)
    EditText rating;
    @BindView(R.id.kopceSave)
    Button kopcesave;
    @BindView(R.id.editText4)
    EditText logo;

    private RestoraniModel restorani;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);
        restorani = PreferencesManager.getRestaurants(this);

    }


    @OnClick (R.id.kopceSave)
    public void saveDetails(){
        Restorani restoran = new Restorani(logo.getText().toString(),name.getText().toString(),lokacija.getText().toString(),rating.getText().toString());
        restorani.restaurants.add(restoran);
        PreferencesManager.addRestaurants(restorani,Main2Activity.this);
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();

    }




}
