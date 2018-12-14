package com.example.jesulonimi.rssreaderapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.exampleViewHolder> {
    private Context c;
   private List<FeedItems> lFt;

    public myAdapter(Context c, List<FeedItems> lFt) {
        this.c = c;
        this.lFt = lFt;
    }

    @NonNull
    @Override
    public myAdapter.exampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.custom_row_items,parent,false);
        return new exampleViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.exampleViewHolder holder, int position) {
        final FeedItems ft=lFt.get(position);
        holder.title.setText(ft.getTitle());
        holder.date.setText(ft.getPubDate());
        holder.description.setText(ft.getDescription());
        Picasso.with(c).load(ft.getThumbnailUrl()).fit().into(holder.imv);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(c,DetailActivity.class);
                i.putExtra("link",ft.getLink());
                c.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return lFt.size();
    }
    public class exampleViewHolder extends RecyclerView.ViewHolder{
TextView title;
ImageView imv;
TextView description;
TextView date;
CardView cardView;
        public exampleViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.myTitle);
            imv=(ImageView) itemView.findViewById(R.id.imageNews);
            description=(TextView) itemView.findViewById(R.id.description);
date=(TextView) itemView.findViewById(R.id.textDate);
cardView=(CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
