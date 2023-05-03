package com.example.milkmantra.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milkmantra.R;

import java.util.ArrayList;

import com.example.milkmantra.model.Customer_Add_Provider_model;

public class Provider_Selection_Adapter  extends RecyclerView.Adapter<Provider_Selection_Adapter.ViewHolder> {

    Context context;
    ArrayList<Customer_Add_Provider_model> provider_selections;


     public  void  setFilteredList(ArrayList<Customer_Add_Provider_model> list){
         provider_selections=list;
         notifyDataSetChanged();
     }
    public Provider_Selection_Adapter(Context context, ArrayList<Customer_Add_Provider_model> provider_selections) {
        this.context = context;
        this.provider_selections = provider_selections;
    }


    /*
    ADD DATA TO ADAPTER
     */
    public void add(Customer_Add_Provider_model s) {
        provider_selections.add(s);
        notifyDataSetChanged();
    }

    public Customer_Add_Provider_model getItem(int position) {
        return provider_selections.get(position);
    }

    /*
    CLEAR DATA FROM ADAPTER
     */
    public void clear() {
        provider_selections.clear();
        notifyDataSetChanged();
    }

    //here we have create whole layout into view
    @NonNull
    @Override
    public Provider_Selection_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(context).inflate(R.layout.custom_add_provider,viewGroup,false);
       ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Provider_Selection_Adapter.ViewHolder viewHolder, int i) {


       // viewHolder.name.setText(provider_selections.get(i).getProvider_name());
        viewHolder.ed1.setText(provider_selections.get(i).getCustomer_morning_cow_volume());
        viewHolder.ed2.setText(provider_selections.get(i).getCustomer_morning_buffelo_volume());
        viewHolder.ed3.setText(provider_selections.get(i).getCustomer_morning_other_volume());
        viewHolder.ed4.setText(provider_selections.get(i).getCustomer_evening_cow_volume());
        viewHolder.ed5.setText(provider_selections.get(i).getCustomer_evening_buffelo_volume());
        viewHolder.ed6.setText(provider_selections.get(i).getCustomer_evening_other_volume());
        viewHolder.lblName.setText(provider_selections.get(i).getProvider_name());
        viewHolder.lblProvider_id.setText(provider_selections.get(i).getProvider_id());






    }

    // here we have specifing the size of list
    @Override
    public int getItemCount() {
        return provider_selections.size();
    }

    // all the operation perform here
    public class ViewHolder extends RecyclerView.ViewHolder  implements  View.OnClickListener{
        EditText ed1,ed2,ed3,ed4,ed5,ed6;
        TextView lblName,lblProvider_id;
        Button btnSaveCustomerMilkDetail;
        int m,e;
        TextView name;
        Switch sw;
        RadioButton rb1,rb2;

        // here we have find id and after the set data into onBindingViewHolder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // name=itemView.findViewById(R.id.Name);

        ed1=itemView.findViewById(R.id.moring_cow);
        ed2=itemView.findViewById(R.id.mornig_buffelow);
        ed3=itemView.findViewById(R.id.mornig_other);
        ed4=itemView.findViewById(R.id.evening_cow);
        ed5=itemView.findViewById(R.id.evenig_buffelow);
        ed6=itemView.findViewById(R.id.eveninig_other);
        lblName=itemView.findViewById(R.id.lblName);
        lblProvider_id=itemView.findViewById(R.id.lblProvider_id);
        btnSaveCustomerMilkDetail=itemView.findViewById(R.id.btnSaveCustomerMilkDetail);
        btnSaveCustomerMilkDetail.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnSaveCustomerMilkDetail:

                    Log.v("ERROR", "##ERROR" + "Save clicked"+lblName.getText().toString());
                    break;
            }
        }
    }
}
