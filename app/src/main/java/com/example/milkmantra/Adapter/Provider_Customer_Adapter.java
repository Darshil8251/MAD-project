package com.example.milkmantra.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.milkmantra.model.Custom_Provider_Home;
import com.example.milkmantra.provider.EndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Provider_Customer_Adapter extends RecyclerView.Adapter<Provider_Customer_Adapter.ViewHolder> {

    static Context context;
    ArrayList<Custom_Provider_Home> customer_list;

    static ConnectionDetector cd;
    static Boolean isInternetPresent = false;


    // here handle the search

    public void setFilteredList(List<Custom_Provider_Home> filteredList, String no) {
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
        this.customer_list = customer_list;
    }

    @NonNull
    @Override

    public Provider_Customer_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_provider_home, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Provider_Customer_Adapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {

        Custom_Provider_Home Customer_Detail = customer_list.get(i);

        viewHolder.textCustomerName.setText(customer_list.get(i).getCustomerName());
        viewHolder.textCustomerId.setText(customer_list.get(i).getCustomerId());
        viewHolder.btnCustomer_Morning_Cow.setText(customer_list.get(i).getCustomerMorningCowVolume());
        viewHolder.btnCustomer_Morning_Buffelow.setText(customer_list.get(i).getCustomerMorningBuffelowVolume());
        viewHolder.btnCustomer_Morning_Other.setText(customer_list.get(i).getCustomerMorningOtherVolume());
        viewHolder.btnCustomer_Evening_Cow.setText(customer_list.get(i).getCustomerEveningCowVolume());
        viewHolder.btnCustomer_Evening_Buffelow.setText(customer_list.get(i).getCustomerEveningBuffelowVolume());
        viewHolder.btnCustomer_Evening_Other.setText(customer_list.get(i).getCustomerEveningOtherVolume());

        viewHolder.btnCustomer_Morning_Cow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog((Button) view,i);
            }
        });



        viewHolder.btnCustomer_Morning_Buffelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog((Button) view,i);
            }
        });

        viewHolder.btnCustomer_Morning_Other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog((Button) view,i);
            }
        });

        viewHolder.btnCustomer_Evening_Cow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog((Button) view,i);
            }
        });

        viewHolder.btnCustomer_Evening_Buffelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog((Button) view,i);
            }
        });

        viewHolder.btnCustomer_Evening_Other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog((Button) view,i);
            }
        });



        // it is for when provider click on accept and approve milkorder
        viewHolder.btnCustomerAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              // it is called when click on accept button

                String Customer_ID = viewHolder.textCustomerId.getText().toString();
                String provider_ID = MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Provider_Id().toString();
                String Customer_Morning_Cow = viewHolder.btnCustomer_Morning_Cow.getText().toString();
                String Customer_Morning_Buffelow = viewHolder.btnCustomer_Morning_Buffelow.getText().toString();
                String Customer_Morning_Other = viewHolder.btnCustomer_Morning_Other.getText().toString();
                String Customer_Evening_Cow = viewHolder.btnCustomer_Evening_Cow.getText().toString();
                String Customer_Evening_Buffelow = viewHolder.btnCustomer_Evening_Buffelow.getText().toString();
                String Customer_Evening_Other = viewHolder.btnCustomer_Evening_Other.getText().toString();

                showDialog_forAccept_milk_order(Customer_ID,provider_ID,Customer_Morning_Cow,Customer_Morning_Buffelow,Customer_Morning_Other,Customer_Evening_Cow,Customer_Evening_Buffelow,Customer_Evening_Other);


               // ProviderAcceptOrder(Customer_ID, provider_ID, Customer_Morning_Cow, Customer_Morning_Buffelow, Customer_Morning_Other, Customer_Evening_Cow, Customer_Evening_Buffelow, Customer_Evening_Other);
            }
        });


        // here handle The Vacation Mode of Customer

        if (customer_list.get(i).getCustomerVacationMode().equals("1")) {
            viewHolder.Customer_Vacation_Mode.setChecked(true);
            viewHolder.Customer_Vacation_Mode.setClickable(false);
        } else {
            viewHolder.Customer_Vacation_Mode.setChecked(false);
            viewHolder.Customer_Vacation_Mode.setClickable(false);
        }

    }

    private void showDialog_forAccept_milk_order(String customer_id, String provider_id, String customer_morning_cow, String customer_morning_buffelow, String customer_morning_other, String customer_evening_cow, String customer_evening_buffelow, String customer_evening_other) {





        Button btnCowMorning,btnBuffelowMorning,btnOtherMorning,btnCowEvening,btnBuffelowEvening,btnOtherEvening;

        Button Accept,Denied;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.custom_provider_accept_order, null);
        builder.setView(dialogView);


        final AlertDialog dialog = builder.create();


        btnCowMorning=dialogView.findViewById(R.id.btnCustomer_Morning_Cow);
        btnBuffelowMorning=dialogView.findViewById(R.id.btnCustomer_Morning_Buffelow);
        btnOtherMorning= dialogView.findViewById(R.id.btnCustomer_Morning_Other);

        btnCowEvening=dialogView.findViewById(R.id.btnCustomer_Evening_Cow);
        btnBuffelowEvening=dialogView.findViewById(R.id.btnCustomer_Evening_Buffelow);
        btnOtherEvening=dialogView.findViewById(R.id.btnCustomer_Evening_Other);

       /* btnCowMorning.setText(customer_morning_cow);
        btnCowEvening.setText(customer_evening_cow);


        btnBuffelowEvening.setText(customer_morning_buffelow);
        btnBuffelowMorning.setText(customer_evening_buffelow);


        btnOtherEvening.setText(customer_morning_other);
        btnOtherMorning.setText(customer_evening_other);*/

        btnCowMorning.setText(customer_morning_cow);
        btnBuffelowMorning.setText(customer_morning_buffelow);
        btnOtherMorning.setText(customer_morning_other);


        btnCowEvening.setText(customer_evening_cow);
        btnBuffelowEvening.setText(customer_evening_buffelow);
        btnOtherEvening.setText(customer_evening_other);



        Accept=dialogView.findViewById(R.id.btnProviderAcceptOrder);
        Denied=dialogView.findViewById(R.id.btnProviderDeniedOrder);

        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProviderAcceptOrder(customer_id, provider_id,customer_morning_cow,customer_morning_buffelow,customer_morning_other,customer_evening_cow,customer_evening_buffelow,customer_evening_other);

                dialog.dismiss();

            }
        });

        Denied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ProviderAcceptOrder(customer_id, provider_id,customer_morning_cow,customer_morning_buffelow,customer_morning_other,customer_evening_cow,customer_evening_buffelow,customer_evening_other);

                dialog.dismiss();

            }
        });



        dialog.show();





    }


    // it is called when click on accept button
    private void ShowDialog(final Button edt,int position) {
        final String[] options = {"1", "2", "3", "4", "5"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select an option");

        // no item initially selected
        builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when an option is selected

                edt.setText(options[which]);


            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                // Handle OK button click here
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();

    }


    // it is called when provider click on accept

    private void ProviderAcceptOrder(String Customer_Id, String Provider_Id, String Cow_Mornig, String Buffelow_Morning, String Other_Morning, String Cow_Evening, String Buffelow_Evening, String Other_Evening) {

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

                            Toast.makeText(context, "Your Milk Ordered SuccessFully", Toast.LENGTH_LONG).show();
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

                    params.put("provider_id", Provider_Id);
                    params.put("customer_id", Customer_Id);
                    params.put("transaction_customer_morning_cow_volume", Cow_Mornig);
                    params.put("transaction_morning_buffelo_volume", Buffelow_Morning);
                    params.put("transaction_morning_other_volume", Other_Morning);
                    params.put("transaction_evening_cow_volume", Cow_Evening);
                    params.put("transaction_evening_buffelo_volume", Buffelow_Evening);
                    params.put("transaction_evening_other_volume", Other_Evening);
                    params.put("transaction_month", "0");
                    params.put("transaction_year", "0");
                    params.put("transaction_day", "0");
                    params.put("transaction_reserve", "0");

                    return params;
                }

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
        TextView textCustomerName, textCustomerId;
        Button btnCustomer_Morning_Cow, btnCustomer_Morning_Buffelow, btnCustomer_Morning_Other, btnCustomer_Evening_Cow,
                btnCustomer_Evening_Buffelow, btnCustomer_Evening_Other;

        Button btnCustomerAccept;
        Switch Customer_Vacation_Mode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textCustomerName = itemView.findViewById(R.id.CustomerName);
            this.textCustomerId = itemView.findViewById(R.id.CustomerId);


            this.btnCustomer_Morning_Cow = itemView.findViewById(R.id.btnCustomer_Morning_Cow);
            this.btnCustomer_Morning_Buffelow = itemView.findViewById(R.id.btnCustomer_Morning_Buffelow);
            this.btnCustomer_Morning_Other = itemView.findViewById(R.id.btnCustomer_Morning_Other);

            this.btnCustomer_Evening_Cow=itemView.findViewById(R.id.btnCustomer_Evening_Cow);
            this.btnCustomer_Evening_Buffelow=itemView.findViewById(R.id.btnCustomer_Evening_Buffelow);
            this.btnCustomer_Evening_Other=itemView.findViewById(R.id.btnCustomer_Evening_Other);


            this.btnCustomerAccept = itemView.findViewById(R.id.CustomerAccept);
            this.Customer_Vacation_Mode = itemView.findViewById(R.id.Customer_Vacation_Mode);

        }


    }
}
