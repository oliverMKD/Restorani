package com.example.oliver.restorani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main4Activity extends AppCompatActivity {

    @BindView(R.id.editIme)
    EditText ime;
    @BindView(R.id.editLink)
    EditText link;
    @BindView(R.id.editCena)
    EditText cena;
    @BindView(R.id.editVeg)
    EditText veg;
    @BindView(R.id.kopceZacuvaj)
    Button kopceZacuvaj;

    private MenuModel menuModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.kopceZacuvaj)
    public void saveDetails(){
        Meni meni = new Meni(cena.getText().toString(),ime.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("meni",meni);
        setResult(RESULT_OK,intent);
        finish();

    }
}
