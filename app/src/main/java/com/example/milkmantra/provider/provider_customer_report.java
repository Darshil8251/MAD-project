package com.example.milkmantra.provider;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.milkmantra.Adapter.Provider_Customer_Report_Adapter;
import com.example.milkmantra.ConnectionDetector;
import com.example.milkmantra.MyApplication_OnlineTransfer;
import com.example.milkmantra.R;
import com.example.milkmantra.model.Custom_Provider_Home;
import com.example.milkmantra.model.Provider_Customer_Report;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class provider_customer_report extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Provider_Customer_Report> customer_list=new ArrayList<>();

    Provider_Customer_Report_Adapter adapter_fb;

    ImageView home;

    ConnectionDetector cd;
    Boolean isInternetPresent = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_customer_report);

        // it for toolbar

        toolbar=findViewById(R.id.ProviderReportToolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Report");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        // handle Home icon

        home=findViewById(R.id.Home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),provider_home.class);
                startActivity(intent);
            }
        });

        // here handle recycleview




        recyclerView=findViewById(R.id.ProviderCustomerReportRecycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter_fb=new Provider_Customer_Report_Adapter(provider_customer_report.this,customer_list);
        recyclerView.setAdapter(adapter_fb);

        findCustomer(provider_customer_report.this);

    }




    private void findCustomer(Context context) {

        adapter_fb.clear();
        //CAll If Internet is available
        cd = new ConnectionDetector(context);
        isInternetPresent = cd.isConnectingToInternet();

        // check for Internet status
        if (isInternetPresent) {

            // Toast.makeText(context, "Name " + name + "Email " + email + " phonenumber " + phonenumber + " maincity " + maincity, Toast.LENGTH_SHORT).show();
            StringRequest strReq = new StringRequest(Request.Method.POST,
                    EndPoints.CUSTOMER_PROVIDER_ASSOCIATION, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //UPDATE PROPETIES

                    try {


                        JSONObject obj = new JSONObject(response);
                        //txtmessage.setText(obj.getString("message"));

                        JSONArray random_usersarray = obj.getJSONArray("random_users");
                        // check for error flag
                        if (random_usersarray.length() != 0) {


                            for (int i = 0; i < random_usersarray.length(); i++) {
                                JSONObject randomuserObj = (JSONObject) random_usersarray.get(i);

                                Log.v("Provider response", randomuserObj.toString());
                                Provider_Customer_Report ObjUsr = new Provider_Customer_Report();
                                //id, name, email, dob, home_Address, phone_number, current_place,
                                // transplace1, transplace2, transplace3, transplace4, transplace5,
                                if (!randomuserObj.getString("customer_id").equals("0")) {

                                    ObjUsr.setName((String) randomuserObj.get("customer_name"));
                                    ObjUsr.setNo(randomuserObj.getString("customer_id"));



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
                    params.put("provider_id", MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Provider_Id().toString());
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

}