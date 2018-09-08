package com.example.dlarb.minimaltodo;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    ArrayList<String> items;

    public RecyclerAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.Title_TV.setText(items.get(position).toString());

    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView Title_TV;

        public ItemViewHolder(final View itemView) {
            super(itemView);
            Title_TV = (TextView) itemView.findViewById(R.id.List_Title);
           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), DetailMemoAcitivty.class);
                    intent.putExtra("position",pos);
                    itemView.getContext().startActivity(intent);
                    ((Activity)itemView.getContext()).finish();
                }
            });
        }
    }
}