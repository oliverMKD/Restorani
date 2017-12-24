package com.example.oliver.restorani;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

import static com.example.oliver.restorani.PreferencesManager.restaurants;

/**
 * Created by Oliver on 12/18/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {
    OnRowClickListener onRowClickListener;

    public RecyclerAdapter(Context context2,OnRowClickListener listener){
        context = context2;
        onRowClickListener = listener;
    }
    Context context;
    List<Restorani> restoraniArrayList = new ArrayList<>();

    public void setItems(List<Restorani> _restoraniArrayList) {
        restoraniArrayList = _restoraniArrayList;
    }





    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recyclerviewrow, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, final int position) {
        final Restorani restorani = restoraniArrayList.get(position);
        holder.textView.setText("Name :" + restorani.name);
        holder.textView2.setText("City :" + restorani.city);
        holder.textView3.setText("Rating :" + restorani.rating);

        Picasso.with(context).load(restorani.logo).fit().centerInside().into(holder.slika);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onRowClickListener.onRowClick(restorani,position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return restoraniArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.slika)
        ImageView slika;
        @BindView(R.id.text)
        TextView textView;
        @BindView(R.id.text2)
        TextView textView2;
        @BindView(R.id.text3)
        TextView textView3;
        @BindView(R.id.linear)
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
