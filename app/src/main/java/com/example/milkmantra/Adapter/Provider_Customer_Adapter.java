package com.example.milkmantra.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.milkmantra.ConnectionDetector;
import com.example.milkmantra.MyApplication_OnlineTransfer;
import com.example.milkmantra.R;
import com.example.milkmantra.customer.create_account_customer;
import com.example.milkmantra.model.Custom_Provider_Home;
import com.example.milkmantra.model.Customer_Add_Provider_model;
import com.example.milkmantra.provider.EndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Provider_Customer_Adapter extends   RecyclerView.Adapter<Provider_Customer_Adapter.ViewHolder>{

    static Context context;
    ArrayList<Custom_Provider_Home>  customer_list;

    static ConnectionDetector cd;
    static Boolean isInternetPresent = false;



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

        Custom_Provider_Home Customer_Detail=customer_list.get(i);

       viewHolder.textCustomerName.setText(customer_list.get(i).getCustomerName());
       viewHolder.textCustomerId.setText( customer_list.get(i).getCustomerId());
        viewHolder.edtCustomer_Morning_Cow.setText( customer_list.get(i).getCustomerMorningCowVolume());
        viewHolder.edtCustomer_Morning_Buffelow.setText( customer_list.get(i).getCustomerMorningBuffelowVolume());
        viewHolder.edtCustomer_Morning_Other.setText(customer_list.get(i).getCustomerMorningOtherVolume());
        viewHolder.edtCustomer_Evening_Cow.setText( customer_list.get(i).getCustomerEveningCowVolume());
        viewHolder.edtCustomer_Evening_Buffelow.setText( customer_list.get(i).getCustomerEveningBuffelowVolume());
        viewHolder.edtCustomer_Evening_Other.setText( customer_list.get(i).getCustomerEveningOtherVolume());


        // it is for when provider click on accept and approve milkorder
        viewHolder.btnCustomerAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProviderAcceptOrder(Customer_Detail);
            }
        });


        // here handle The Vacation Mode of Customer

        if(customer_list.get(i).getCustomerVacationMode().equals("0")){
            viewHolder.Customer_Vacation_Mode.setChecked(true);
        }
        else {
            viewHolder.Customer_Vacation_Mode.setChecked(false);
        }

    }



    // it is called when provider click on accept

    private void ProviderAcceptOrder(Custom_Provider_Home customer_detail) {

        cd = new ConnectionDetector(context);
        isInternetPresent = cd.isConnectingToInternet();

        // check for Internet status
        if (isInternetPresent) {

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    EndPoints.PROVIDER_ACCEPT_ORDERED, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //UPDATE PROPETIES

                    try {

                        JSONObject obj = new JSONObject(response);
                        //txtmessage.setText(obj.getString("message"));

                        // check for error flag
                        if (obj.getBoolean("error") == false) {

                            Toast.makeText(context,"Your Milk Ordered SuccessFully",Toast.LENGTH_LONG).show();
                        } else {
                            // error in fetching chat rooms
                            Toast.makeText(context, "Check Internet Connection.#1", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        Toast.makeText(context, "Check Internet Connection.#2" + e.toString(), Toast.LENGTH_SHORT).show();
                    }


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    NetworkResponse networkResponse = error.networkResponse;
                    Toast.makeText(context, "Check Internet Connection.#3" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("provider_id",customer_detail.getProviderId());
                    params.put("customer_id",customer_detail.getCustomerId());
                    params.put("transaction_customer_morning_cow_volume",customer_detail.getCustomerMorningCowVolume());
                    params.put("transaction_morning_buffelo_volume",customer_detail.getCustomerMorningBuffelowVolume());
                    params.put("transaction_morning_other_volume",customer_detail.getCustomerMorningOtherVolume());
                    params.put("transaction_evening_cow_volume",customer_detail.getCustomerEveningCowVolume());
                    params.put("transaction_evening_buffelo_volume",customer_detail.getCustomerEveningBuffelowVolume());
                    params.put("transaction_evening_other_volume",customer_detail.getCustomerEveningOtherVolume());
                    params.put("transaction_month", "0");
                    params.put("transaction_year", "0");
                    params.put("transaction_day", "0");
                    params.put("transaction_reserve", "0");

                    return params;
                }

                ;
            };
            // disabling retry policy so that it won't make
            // multiple http calls
            int socketTimeout = 0;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

            strReq.setRetryPolicy(new DefaultRetryPolicy(0, -1, 0));
            //Adding request to request queue
            MyApplication_OnlineTransfer.getInstance().addToRequestQueue(strReq);

        } else {
            // txtsent_0_row.setText("Check Internet connection & Retry.");
            //btnrequestsent_retry.setVisibility(View.VISIBLE);
            Toast.makeText(context, "Check Internet connection & Retry.", Toast.LENGTH_SHORT).show();
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
