package com.example.oliver.restorani;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oliver on 12/20/2017.
 */

public class RecyclerAdapterMenu extends RecyclerView.Adapter <RecyclerAdapterMenu.ViewHolder>{
    OnRecyclerViewClickListenerMenu onRecyclerViewClickListenerMenu;

    public RecyclerAdapterMenu(Context context3, OnRecyclerViewClickListenerMenu listenerMenu){
        context = context3;
        onRecyclerViewClickListenerMenu = listenerMenu;
    }
    Context context;
    List<Meni> menu = new ArrayList<>();


    public void setItems(List<Meni> _meniList) {
        menu = _meniList;
    }


    @Override
    public RecyclerAdapterMenu.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recyclerviewrow_menu, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterMenu.ViewHolder holder,final int position) {
        final Meni meni = menu.get(position);
        holder.textView.setText(meni.link);
        holder.textView2.setText("proce : " +meni.price);
        holder.textView3.setText("foodname :"+meni.foodname);


        Picasso.with(context).load(meni.link).fit().centerInside().into(holder.slika);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerViewClickListenerMenu.onRowClick(meni,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(menu == null)
            return  0;
        return menu.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.slika1)
        ImageView slika;
        @BindView(R.id.text)
        TextView textView;
        @BindView(R.id.text2)
        TextView textView2;
        @BindView(R.id.text3)
        TextView textView3;
        @BindView(R.id.linear1)
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
