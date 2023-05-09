package com.example.milkmantra.Adapter;

import static android.support.v4.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milkmantra.R;
import com.example.milkmantra.customer_card;
import com.example.milkmantra.model.Provider_Customer_Report;
import com.example.milkmantra.provider.provider_home;

import java.util.ArrayList;

public class Provider_Customer_Report_Adapter extends  RecyclerView.Adapter<Provider_Customer_Report_Adapter.ViewHolder> {
    Context context;
    ArrayList<Provider_Customer_Report> customer_list=new ArrayList<>();

    public Provider_Customer_Report_Adapter(Context context, ArrayList<Provider_Customer_Report> customer_list) {
        this.context = context;
        this.customer_list=customer_list;
    }



    public void clear() {
        customer_list.clear();
        notifyDataSetChanged();
    }

    public void add(Provider_Customer_Report s) {
        customer_list.add(s);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Provider_Customer_Report_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_provider_customer_report,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Provider_Customer_Report_Adapter.ViewHolder viewHolder, int i) {

        viewHolder.CustomerNumber.setText(customer_list.get(i).getNo());
        viewHolder.CustomerName.setText(customer_list.get(i).getName());

      viewHolder.btnCustomerReportCard.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(context, customer_card.class);
              intent.putExtra("CustomerName",customer_list.get(i).getName());
              startActivity(context, intent, null);

          }
      });

    }

    @Override
    public int getItemCount() {
        return customer_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView CustomerNumber,CustomerName;

        Button btnCustomerReportCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            CustomerName=itemView.findViewById(R.id.CustomerName);
            CustomerNumber=itemView.findViewById(R.id.CustomerNumber);

            btnCustomerReportCard=itemView.findViewById(R.id.CustomerCard);

        }
    }
}
