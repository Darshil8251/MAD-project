package com.example.milkmantra.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milkmantra.R;
import com.example.milkmantra.customer.create_account_customer;
import com.example.milkmantra.model.Custom_Provider_Home;
import com.example.milkmantra.model.Customer_Add_Provider_model;

import java.util.ArrayList;
import java.util.List;

public class Provider_Customer_Adapter extends   RecyclerView.Adapter<Provider_Customer_Adapter.ViewHolder>{

    Context context;
    ArrayList<Custom_Provider_Home>  customer_list;


    // here handle the search

    public void setFilteredList(List<Custom_Provider_Home> filteredList,String no) {
        this.customer_list = (ArrayList<Custom_Provider_Home>) filteredList;
        notifyDataSetChanged();

    }

    public void clear() {
        customer_list.clear();
        notifyDataSetChanged();
    }

    public void add(Custom_Provider_Home s) {
        customer_list.add(s);
        notifyDataSetChanged();
    }

    public Provider_Customer_Adapter(Context context, ArrayList<Custom_Provider_Home> customer_list) {
        this.context = context;
        this.customer_list=customer_list;
    }

    @NonNull
    @Override

    public Provider_Customer_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_provider_home,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Provider_Customer_Adapter.ViewHolder viewHolder, int i) {
       viewHolder.textCustomerName.setText(customer_list.get(i).getCustomerName());
       viewHolder.textCustomerId.setText( customer_list.get(i).getCustomerId());
        viewHolder.edtCustomer_Morning_Cow.setText( customer_list.get(i).getCustomerMorningCowVolume());
        viewHolder.edtCustomer_Morning_Buffelow.setText( customer_list.get(i).getCustomerMorningBuffelowVolume());
        viewHolder.edtCustomer_Morning_Other.setText(customer_list.get(i).getCustomerMorningOtherVolume());
        viewHolder.edtCustomer_Evening_Cow.setText( customer_list.get(i).getCustomerEveningCowVolume());
        viewHolder.edtCustomer_Evening_Buffelow.setText( customer_list.get(i).getCustomerEveningBuffelowVolume());
        viewHolder.edtCustomer_Evening_Other.setText( customer_list.get(i).getCustomerEveningOtherVolume());


        // here handle The Vacation Mode of Customer

        if(customer_list.get(i).getCustomerVacationMode().equals("0")){
            viewHolder.Customer_Vacation_Mode.setChecked(true);
        }
        else {
            viewHolder.Customer_Vacation_Mode.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return customer_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textCustomerName,textCustomerId;
        EditText edtCustomer_Morning_Cow,edtCustomer_Morning_Buffelow,edtCustomer_Morning_Other,edtCustomer_Evening_Cow,
                edtCustomer_Evening_Buffelow,edtCustomer_Evening_Other;

        Button btnCustomerAccept,btnCustomerDenied;
        Switch Customer_Vacation_Mode;
          public ViewHolder(@NonNull View itemView) {
            super(itemView);
           textCustomerName=itemView.findViewById(R.id.CustomerName);
              textCustomerId=itemView.findViewById(R.id.CustomerId);
              edtCustomer_Morning_Cow=itemView.findViewById(R.id.Customer_Morning_Cow);
              edtCustomer_Morning_Buffelow=itemView.findViewById(R.id.Customer_Morning_Buffelow);
              edtCustomer_Morning_Other=itemView.findViewById(R.id.Customer_Morning_Other);
              edtCustomer_Evening_Cow=itemView.findViewById(R.id.Customer_Evening_Cow);
              edtCustomer_Evening_Buffelow=itemView.findViewById(R.id.Customer_Evening_Buffelow);
              edtCustomer_Evening_Other=itemView.findViewById(R.id.Customer_Evening_Other);
              btnCustomerAccept=itemView.findViewById(R.id.CustomerAccept);
              btnCustomerDenied=itemView.findViewById(R.id.CustomerDenied);
              Customer_Vacation_Mode=itemView.findViewById(R.id.Customer_Vacation_Mode);
        }
    }
}
