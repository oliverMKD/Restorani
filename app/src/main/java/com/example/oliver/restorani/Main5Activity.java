package com.example.oliver.restorani;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main5Activity extends AppCompatActivity {

    private RestoraniModel restorani;

    Menu meni;
    Restorani restorani2;
    @BindView(R.id.pager)
    ViewPager viewPager;
    ViewAdapter adapter;
    public int menu_pozicija=0;
    public  int pozicija = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ButterKnife.bind(this);


        restorani = PreferencesManager.getRestaurants(this);

        Intent intent = getIntent();
        if (intent.hasExtra("novoextra")){
            restorani2 = (Restorani) intent.getSerializableExtra("novoextra");
            pozicija = intent.getIntExtra("pozicija_restoran",0);
            menu_pozicija = intent.getIntExtra("pozicija",0);
        }

        adapter = new ViewAdapter(Main5Activity.this.getSupportFragmentManager());
        adapter.AddSliki(restorani2.menu);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(pozicija);
    }

    private void setUpViewPager(ViewPager viewPager) {


    }
}
