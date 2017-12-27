package com.example.oliver.restorani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    @BindView(R.id.buttonEdit1)
    Button kopceEdit;
    int kluc = 1000;
    Meni meni;
    RestoraniModel restorani2;
    int pozicija = 0;
    private int addMenu=1001;
    int kluc1 = 1001;

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
        if(restorani.getLogo()!=null && !restorani.getLogo().isEmpty())
        Picasso.with(this).load(restorani.getLogo()).fit().centerInside().into(slika1);
        ime2.setText("Name :" + restorani.getName());
        grad2.setText("City :" + restorani.getCity());
        rating2.setText("Rating" +restorani.getRating());




        adapter =  new RecyclerAdapterMenu(this, new OnRecyclerViewClickListenerMenu() {
            @Override
            public void onRowClick(Meni meni, int position) {
                Intent intent;
                intent = new Intent(Main3Activity.this,Main5Activity.class);
                intent.putExtra("novoextra",restorani);
                intent.putExtra("pozicija",position);
                intent.putExtra("pozicija_restoran",pozicija);
                startActivityForResult(intent,kluc);

            }
        });

        adapter.setItems(getList());
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView1.setAdapter(adapter);
    }
    ArrayList<Meni> getList() {
        if (restorani.menu !=null && restorani.menu.isEmpty()) {
            Toast.makeText(this, "empty menu", Toast.LENGTH_SHORT).show();
        } else {

        }return restorani.menu;
    }

    @OnClick(R.id.kopceZacuvaj)
    public void Vnesi() {

        Intent intent = new Intent(this, Main4Activity.class);
        intent.putExtra("pozicija",pozicija);
        startActivityForResult(intent, kluc);
    }
    @OnClick(R.id.buttonEdit1)
    public void Edit2() {

        Intent intent1 = new Intent(this, Main6Activity.class);
        intent1.putExtra("pozicija1",pozicija);
        startActivityForResult(intent1, kluc1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==kluc&&resultCode==RESULT_OK){
            restorani2 = PreferencesManager.getRestaurants(this);
            restorani = restorani2.restaurants.get(pozicija);
            adapter.setItems(getList());
            adapter.notifyDataSetChanged();
        }
    }

}
