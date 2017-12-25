package com.example.oliver.restorani;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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
    @BindView(R.id.opengallery)
    Button openGallery;
    @BindView(R.id.slikagalerija)
    ImageView slikagalerija;


    private MenuModel menuModel;
    RestoraniModel restorani;
    int pozicija=0;
    Uri pickedImage;
    String slika = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);
        String logo="https://pbs.twimg.com/profile_images/2215476833/homa_logo_400x400.png";
        link.setText(logo);
        pozicija = getIntent().getIntExtra("pozicija",0);
        restorani = PreferencesManager.getRestaurants(this);
    }

    @OnClick(R.id.kopceZacuvaj)
    public void saveDetails(){
        Meni meni = new Meni(cena.getText().toString(),ime.getText().toString(),link.getText().toString());
        restorani.restaurants.get(pozicija).menu.add(meni);
        PreferencesManager.addRestaurants(restorani,this);
        Intent intent = new Intent(this,Main3Activity.class);
        intent.putExtra("meni",meni);
        setResult(RESULT_OK,intent);
        finish();

    }
    @OnClick(R.id.opengallery)
    public void Gallery(){

        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 1111);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1111){

            pickedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            slika = pickedImage.toString();
            Picasso.with(this).load(pickedImage).centerInside().fit().into(slikagalerija);





        }
    }
}
