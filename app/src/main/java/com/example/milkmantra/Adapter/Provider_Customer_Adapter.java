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
import android.widget.PopupWindow;
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

    public void add(Customer_Add_Provider_model s) {
        //customer_list.add(s);
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
        ViewHolder.number.setText(customer_list.get(i).getNo());
        viewHolder.name.setText(customer_list.get(i).getName());

        viewHolder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              showPopup(view);
            }

            private void showPopup(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View dialogView = LayoutInflater.from(context).inflate(R.layout.provider_order_popup, null);
                    builder.setView(dialogView);

                    final AlertDialog dialog = builder.create();



                    Button cancelButton = dialogView.findViewById(R.id.Denied);
                    cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    Button confirmButton = dialogView.findViewById(R.id.Accept);
                    confirmButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Perform action for skipping
                            dialog.dismiss();

                        }
                    });
                dialog.setCanceledOnTouchOutside(false);

                    dialog.show();
                }

        });

    }

    @Override
    public int getItemCount() {
        return customer_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        static TextView number;
        Button order;
          public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.Name);
            number=itemView.findViewById(R.id.Number);
            order=itemView.findViewById(R.id.Order);
        }
    }
}
