package com.example.milkmantra.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.milkmantra.R;
import com.example.milkmantra.model.Customer_Card_Model;

import java.util.ArrayList;

public class Customer_Card_Adapter extends  RecyclerView.Adapter<Customer_Card_Adapter.ViewHolder>{



    Context context;
    ArrayList<Customer_Card_Model> card_data=new ArrayList<>();


    public Customer_Card_Adapter(Context context, ArrayList<Customer_Card_Model> card_data) {
        this.context = context;
        this.card_data = card_data;
    }

    public void clear() {
        card_data.clear();
        notifyDataSetChanged();
    }

    public void add(Customer_Card_Model s) {
        card_data.add(s);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Customer_Card_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.custom_customer_card,viewGroup,false);
        Customer_Card_Adapter.ViewHolder viewHolder=new Customer_Card_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Customer_Card_Adapter.ViewHolder viewHolder, int i) {

        viewHolder.date.setText(card_data.get(i).getTransaction_day());
        viewHolder.CowMorning.setText(card_data.get(i).getTransaction_customer_morning_cow_volume());
        viewHolder.BuffelowMorning.setText(card_data.get(i).getTransaction_morning_buffelo_volume());
        viewHolder.OtherMorning.setText(card_data.get(i).getTransaction_morning_other_volume());

        viewHolder.CowEvening.setText(card_data.get(i).getTransaction_evening_cow_volume());
        viewHolder.BuffelowEvening.setText(card_data.get(i).getTransaction_evening_buffelo_volume());
        viewHolder.OtherEvening.setText(card_data.get(i).getTransaction_evening_other_volume());

    }

    @Override
    public int getItemCount() {
        return card_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date,CowMorning,BuffelowMorning,OtherMorning,CowEvening,BuffelowEvening,OtherEvening;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            date=itemView.findViewById(R.id.CustomerDate);
            CowMorning=itemView.findViewById(R.id.CustomerMorningCow);
            BuffelowMorning=itemView.findViewById(R.id.CustomerMorningBuffelow);
            OtherMorning=itemView.findViewById(R.id.CustomerMorningOther);
            CowEvening=itemView.findViewById(R.id.CustomerEveningCow);
            BuffelowEvening=itemView.findViewById(R.id.CustomerEveningBuffelow);
            OtherEvening=itemView.findViewById(R.id.CustomerEveningOther);

        }
    }
}
