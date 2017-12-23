package com.example.oliver.restorani;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Oliver on 12/23/2017.
 */

public class ViewAdapter extends FragmentPagerAdapter {
    ArrayList<Meni> menu = new ArrayList<>();

    public void AddSliki(ArrayList<Meni> _menu){
        menu = _menu;

    }

    public ViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();

        args.putString("imageUrl", menu.get(position).getLink());
        
        Fragment1 fragment = new Fragment1();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return menu.size();
    }
}
