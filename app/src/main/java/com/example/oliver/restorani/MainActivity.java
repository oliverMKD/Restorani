package com.example.oliver.restorani;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.oliver.restorani.PreferencesManager.restaurants;

public class MainActivity extends AppCompatActivity {
    RecyclerAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.kopce)
    Button kopceNext;
    int kluc = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter =  new RecyclerAdapter(this, new OnRowClickListener() {
            @Override
            public void onRowClick(Restorani restaurants, int position) {
                Intent intent;
                intent = new Intent(MainActivity.this,Main3Activity.class);
                intent.putExtra("extra",restaurants);
                intent.putExtra("pozicija",position);
                startActivity(intent);
        }
        });

        adapter.setItems(getList());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(adapter);
    }
    ArrayList<Restorani> getList() {
        RestoraniModel restoraniModel = PreferencesManager.getRestaurants(this);
        return restoraniModel.restaurants;
    }

    @OnClick(R.id.kopce)
    public void Vnesi() {

        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, kluc);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==kluc&&resultCode==RESULT_OK);{
            adapter.setItems(getList());
            adapter.notifyDataSetChanged();

        }
    }
}
