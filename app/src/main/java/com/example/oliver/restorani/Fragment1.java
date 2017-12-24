package com.example.oliver.restorani;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Oliver on 12/23/2017.
 */

public class Fragment1 extends Fragment {

    String urlImage;
    String ime;
    String cena1;

    private Unbinder mUnbinder;
    @BindView(R.id.slikaslika)
    ImageView slika;
    @BindView(R.id.naziv)
    EditText naziv;
    @BindView(R.id.cena)
    EditText cena;
    @BindView(R.id.kopceEdit)
    Button edit;
    Meni meni;
    int kluc = 1000;
    private RestoraniModel restorani;
    public int menu_pozicija=0;
    public  int pozicija = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,null);
        mUnbinder = ButterKnife.bind(this, view);
        ime = getArguments().getString("ime");
        cena1 = getArguments().getString("cena");
        urlImage = getArguments().getString("imageUrl");
        naziv.setText(ime);
        cena.setText(cena1);
        Picasso.with(getActivity()).load(urlImage).fit().centerInside().into(slika);
        restorani  = PreferencesManager.getRestaurants(getActivity());
        menu_pozicija = ((Main5Activity)getActivity()).menu_pozicija;
        pozicija = ((Main5Activity)getActivity()).pozicija;

        return view;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mUnbinder.unbind();
    }
    @OnClick(R.id.kopceEdit)
    public void saveMeni(){
        meni = new Meni(naziv.getText().toString(),cena.getText().toString());

        restorani.restaurants.get(pozicija).menu.set(menu_pozicija,meni);
        PreferencesManager.addRestaurants(restorani,getActivity());
        Intent intent = new Intent(getActivity(),Main3Activity.class);
        intent.putExtra("meni",meni);
        getActivity().setResult(RESULT_OK,intent);
        getActivity().finish();



    }
}
