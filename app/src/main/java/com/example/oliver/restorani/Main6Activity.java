package com.example.oliver.restorani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main6Activity extends AppCompatActivity {
    private MenuModel menuModel;
    RestoraniModel restorani;
    int pozicija=0;
    @BindView(R.id.editNaziv)
    EditText editNaziv;
    @BindView(R.id.editGrad)
    EditText editGrad;
    @BindView(R.id.editRating)
    EditText editRating;
    Restorani restorani2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        ButterKnife.bind(this);



        Intent intent = getIntent();
        if (intent.hasExtra("pozicija1")) {

            int pozicija = intent.getIntExtra("pozicija1",0);
            restorani2 = PreferencesManager.getRestaurants(this).restaurants.get(pozicija);


            editNaziv.setText(restorani2.getName());
            editGrad.setText(restorani2.getCity());
            editRating.setText(restorani2.getRating());
        }
    }

    @OnClick(R.id.kopceEdit1)
    public void Klik(View view){
        Restorani rest = new Restorani();

        rest.setName(editNaziv.getText().toString());
        rest.setCity(editGrad.getText().toString());
        rest.setRating(editRating.getText().toString());

        Intent intent2 = new Intent();
        intent2.putExtra("edit_restoran",rest);
        setResult(RESULT_OK,intent2);
        finish();

    }

}
