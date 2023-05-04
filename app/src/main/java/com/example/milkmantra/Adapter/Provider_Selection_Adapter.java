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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.milkmantra.model.Customer_Add_Provider_model;
import com.example.milkmantra.provider.EndPoints;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Provider_Selection_Adapter extends RecyclerView.Adapter<Provider_Selection_Adapter.ViewHolder> {

    Context context;
    ArrayList<Customer_Add_Provider_model> provider_selections;

    ConnectionDetector cd;
    Boolean isInternetPresent = false;


    public void setFilteredList(ArrayList<Customer_Add_Provider_model> list) {
        provider_selections = list;
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
        View view = LayoutInflater.from(context).inflate(R.layout.custom_add_provider, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
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
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        EditText ed1, ed2, ed3, ed4, ed5, ed6;
        TextView lblName, lblProvider_id;
        Button btnSaveCustomerMilkDetail;
        int m, e;
        TextView name;
        Switch sw;
        RadioButton rb1, rb2;

        // here we have find id and after the set data into onBindingViewHolder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // name=itemView.findViewById(R.id.Name);

            ed1 = itemView.findViewById(R.id.moring_cow);
            ed2 = itemView.findViewById(R.id.mornig_buffelow);
            ed3 = itemView.findViewById(R.id.mornig_other);
            ed4 = itemView.findViewById(R.id.evening_cow);
            ed5 = itemView.findViewById(R.id.evenig_buffelow);
            ed6 = itemView.findViewById(R.id.eveninig_other);
            lblName = itemView.findViewById(R.id.lblName);
            lblProvider_id = itemView.findViewById(R.id.lblProvider_id);
            btnSaveCustomerMilkDetail = itemView.findViewById(R.id.btnSaveCustomerMilkDetail);
            btnSaveCustomerMilkDetail.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnSaveCustomerMilkDetail:
                    //CAll If Internet is available
                    cd = new ConnectionDetector(context);
                    isInternetPresent = cd.isConnectingToInternet();

                    // check for Internet status
                    if (isInternetPresent) {

                        // Toast.makeText(context, "Name " + name + "Email " + email + " phonenumber " + phonenumber + " maincity " + maincity, Toast.LENGTH_SHORT).show();
                        StringRequest strReq = new StringRequest(Request.Method.POST,
                                EndPoints.SAVE_PROVIDER, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //UPDATE PROPETIES

                                try {

                                    JSONObject obj = new JSONObject(response);
                                    //txtmessage.setText(obj.getString("message"));

                                    // check for error flag
                                    if (obj.getBoolean("error") == false) {

                                        Toast.makeText(context,"Your Information Added SuccessFully",Toast.LENGTH_LONG).show();
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

                                params.put("provider_id", "1");
                                params.put("customer_id", "3");
                                params.put("customer_vacation_mode", "1");
                                params.put("customer_morning_cow_volume", "3");
                                params.put("customer_morning_buffelo_volume", "3");
                                params.put("customer_morning_other_volume", "3");
                                params.put("customer_evening_cow_volume", "3");
                                params.put("customer_evening_buffelo_volume", "3");
                                params.put("customer_evening_other_volume", "3");


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


                    break;


            }
        }
    }
}
