package com.example.oliver.restorani;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Oliver on 12/23/2017.
 */

public class Fragment1 extends Fragment {

    String urlImage;

    private Unbinder mUnbinder;
    @BindView(R.id.slikaslika)
    ImageView slika;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,null);
        mUnbinder = ButterKnife.bind(this, view);


        urlImage = getArguments().getString("imageUrl");
        Picasso.with(getActivity()).load(urlImage).fit().centerInside().into(slika);
        return view;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mUnbinder.unbind();
    }
}