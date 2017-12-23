package com.example.oliver.restorani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {
    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView1;
    Restorani restorani;
    @BindView(R.id.slika1)
    ImageView slika1;
    @BindView(R.id.ime2)
    TextView ime2;
    @BindView(R.id.grad2)
    TextView grad2;
    @BindView(R.id.rating2)
    TextView rating2;
    RecyclerAdapterMenu adapter;
    @BindView(R.id.kopceZacuvaj)
    Button kopce;
    int kluc = 1000;
    Meni meni;
    RestoraniModel restorani2;
    int pozicija = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        restorani2 = PreferencesManager.getRestaurants(this);

        Intent intent = getIntent();
        if (intent.hasExtra("extra")){
            restorani = (Restorani)intent.getSerializableExtra("extra");
            pozicija = intent.getIntExtra("pozicija",0);
        }
        Picasso.with(this).load(restorani.getLogo()).fit().centerInside().into(slika1);
        ime2.setText(restorani.getName());
        grad2.setText(restorani.getCity());
        rating2.setText(restorani.getRating());

        adapter =  new RecyclerAdapterMenu(this, new OnRecyclerViewClickListenerMenu() {
            @Override
            public void onRowClick(Meni meni, int position) {
                Intent intent;
                intent = new Intent(Main3Activity.this,Main5Activity.class);
                intent.putExtra("novoextra",restorani);
                intent.putExtra("pozicija",position);
                startActivityForResult(intent,kluc);

            }
        });

        adapter.setItems(getList());
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView1.setAdapter(adapter);
    }
    ArrayList<Meni> getList() {
        return restorani.menu;
    }

    @OnClick(R.id.kopceZacuvaj)
    public void Vnesi() {

        Intent intent = new Intent(this, Main4Activity.class);
        startActivityForResult(intent, kluc);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==kluc&&resultCode==RESULT_OK);{
            meni =(Meni)data.getSerializableExtra("meni");
            restorani.menu.add(meni);
            restorani2.restaurants.remove(pozicija);
            restorani2.restaurants.add(pozicija,restorani);
            PreferencesManager.addRestaurants(restorani2,this);


            adapter.notifyDataSetChanged();


        }
    }
}
