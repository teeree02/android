package com.example.projetmobile2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewHolder>{
    private Context context;
    private ArrayList<Restaurant> list;

    private OnItemClickListener itemClickListener;


    public CustomViewAdapter(Context context, ArrayList<Restaurant> list,OnItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.single_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.textName.setText(list.get(position).getNomRestaurant());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position, list.get(position));
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public void remove(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position, Restaurant restaurant);
    }
}
