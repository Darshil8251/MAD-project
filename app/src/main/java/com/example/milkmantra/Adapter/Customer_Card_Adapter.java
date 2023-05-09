package com.example.milkmantra.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.milkmantra.model.Customer_Card_Model;

import java.util.ArrayList;

public class Customer_Card_Adapter extends  RecyclerView.Adapter<Customer_Card_Adapter.ViewHolder>{



    Context context;
    ArrayList<Customer_Card_Model> card_data=new ArrayList<>();


    public Customer_Card_Adapter(Context context, ArrayList<Customer_Card_Model> card_data) {
        this.context = context;
        this.card_data = card_data;
    }

    @NonNull
    @Override
    public Customer_Card_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Customer_Card_Adapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
