package com.example.milkmantra;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.milkmantra.Adapter.Customer_Card_Adapter;
import com.example.milkmantra.model.Custom_Provider_Home;
import com.example.milkmantra.model.Customer_Card_Model;
import com.example.milkmantra.provider.EndPoints;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class customer_card extends AppCompatActivity {

    Toolbar toolbar;
    TextView CustomerName;

    String IntentExtraCustomerName,IntentExtraCustomerNumber;


    RecyclerView recyclerView;

    Customer_Card_Adapter adapter_fb;

    ArrayList<Customer_Card_Model> card_data=new ArrayList<>();

    ConnectionDetector cd;
    Boolean isInternetPresent = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_card);

        // it for handling toolbar
        //  toolbar = findViewById(R.id.ProviderCustomerCard);
        // setSupportActionBar(toolbar);

        // if (getSupportActionBar() != null) {
        //  getSupportActionBar().setTitle("Customer Card");
        //}


        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {

            IntentExtraCustomerName = (String) b.get("CustomerName");
            IntentExtraCustomerNumber=(String) b.get("CustomerNo");
        } else {

            IntentExtraCustomerName="";
            IntentExtraCustomerNumber="";

        }

        CustomerName=findViewById(R.id.CustomerName);

        CustomerName.setText(IntentExtraCustomerName);



        recyclerView=findViewById(R.id.RecycleViewOfCustomerCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        adapter_fb=new Customer_Card_Adapter(this,card_data);

        recyclerView.setAdapter(adapter_fb);


        getDetail(this);






    }

    private void getDetail(Context context) {



        adapter_fb.clear();
        //CAll If Internet is available
        cd = new ConnectionDetector(context);
        isInternetPresent = cd.isConnectingToInternet();

        // check for Internet status
        if (isInternetPresent) {


            StringRequest strReq = new StringRequest(Request.Method.POST,
                    EndPoints.CARD_DATA, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.v("error","#######"+response);
                    //UPDATE PROPETIES

                    try {

                        JSONObject obj = new JSONObject(response);
                        //txtmessage.setText(obj.getString("message"));


                        // check for error flag
                        if (obj.getBoolean("error") == false) {

                            JSONArray random_usersarray = obj.getJSONArray("random_users");

                            for (int i = 0; i < random_usersarray.length(); i++) {
                                JSONObject randomuserObj = (JSONObject) random_usersarray.get(i);
                                Customer_Card_Model ObjUsr = new Customer_Card_Model();

                                if (!randomuserObj.getString("customer_id").equals("0")) {

                                    Log.v("Provider response", "#######");
                                    ObjUsr.setCustomer_id(randomuserObj.getString("customer_id"));
                                    ObjUsr.setProvider_id(randomuserObj.getString("provider_id"));
                                    ObjUsr.setTransaction_id(randomuserObj.getString("transaction_id"));


                                    ObjUsr.setTransaction_customer_morning_cow_volume(randomuserObj.getString("transaction_customer_morning_cow_volume"));
                                    ObjUsr.setTransaction_morning_buffelo_volume(randomuserObj.getString("transaction_morning_buffelo_volume"));
                                    ObjUsr.setTransaction_morning_other_volume(randomuserObj.getString("transaction_morning_other_volume"));


                                    ObjUsr.setTransaction_evening_cow_volume(randomuserObj.getString("transaction_evening_cow_volume"));
                                    ObjUsr.setTransaction_evening_buffelo_volume(randomuserObj.getString("transaction_evening_buffelo_volume"));
                                    ObjUsr.setTransaction_evening_other_volume(randomuserObj.getString("transaction_evening_other_volume"));


                                    ObjUsr.setTransaction_is_paid(randomuserObj.getString("transaction_is_paid"));
                                    ObjUsr.setTransaction_day(randomuserObj.getString("transaction_day"));
                                    ObjUsr.setTransaction_month(randomuserObj.getString("transaction_month"));
                                    ObjUsr.setTransaction_year(randomuserObj.getString("transaction_year"));
                                    ObjUsr.setTransaction_is_active(randomuserObj.getString("transaction_is_active"));
                                    ObjUsr.setTransaction_timestamp(randomuserObj.getString("transaction_timestamp"));


                                    adapter_fb.add(ObjUsr);
                                } else {
                                    Toast.makeText(context, "No more data available.", Toast.LENGTH_SHORT).show();

                                }

                            }
                            adapter_fb.notifyDataSetChanged();

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


                    // here we have take provider_id from sharepreference
                    params.put("provider_id", "1");
                    params.put("customer_id","3");
                    params.put("transaction_day","5");
                    params.put("transaction_month","5");
                    params.put("transaction_year","2023");
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
}

