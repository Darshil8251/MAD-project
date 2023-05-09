package com.example.milkmantra;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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

    String IntentExtraCustomerName;


    RecyclerView recyclerView;

    Customer_Card_Model adapter_fb;

    ArrayList<Customer_Card_Model> card_data=new ArrayList<>();

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
        } else {

            IntentExtraCustomerName="";
        }

        CustomerName=findViewById(R.id.CustomerName);

        CustomerName.setText(IntentExtraCustomerName);



        recyclerView=findViewById(R.id.RecycleViewOfCustomerCard);
        recyclerView.setLayoutManager(new GridLayoutManager(this,7));
        adapter_fb=new Customer_Card_Model();


       // getDetail(this);






        }

    /*private void getDetail(Context context) {



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
                                    Custom_Provider_Home ObjUsr = new Custom_Provider_Home();
                                    //id, name, email, dob, home_Address, phone_number, current_place,
                                    // transplace1, transplace2, transplace3, transplace4, transplace5,
                                    if (!randomuserObj.getString("customer_id").equals("0")) {
                                        ObjUsr.setCustomerId(randomuserObj.getString("customer_id"));
                                        ObjUsr.setProviderId(randomuserObj.getString("provider_id"));
                                        ObjUsr.setCustomerName(randomuserObj.getString("customer_name"));
                                        ObjUsr.setCustomerVacationMode(randomuserObj.getString("customer_vacation_mode"));

                                        ObjUsr.setCustomerMorningCowVolume(randomuserObj.getString("customer_morning_cow_volume"));
                                        ObjUsr.setCustomerMorningBuffelowVolume(randomuserObj.getString("customer_morning_buffelo_volume"));
                                        ObjUsr.setCustomerMorningOtherVolume(randomuserObj.getString("customer_morning_other_volume"));


                                        ObjUsr.setCustomerEveningCowVolume(randomuserObj.getString("customer_evening_cow_volume"));
                                        ObjUsr.setCustomerEveningBuffelowVolume(randomuserObj.getString("customer_evening_buffelo_volume"));
                                        ObjUsr.setCustomerEveningOtherVolume(randomuserObj.getString("customer_evening_other_volume"));

                                        ObjUsr.setCustomerPhoneNumber(randomuserObj.getString("customer_phone_number"));
                                        ObjUsr.setCustomerAddress(randomuserObj.getString("customer_address"));
                                        ObjUsr.setCustomerPincode(randomuserObj.getString("customer_pincode"));
                                        ObjUsr.setCustomerIsActive(randomuserObj.getString("customer_is_active"));
                                        ObjUsr.setCustomerUniqueNo(randomuserObj.getString("customer_unique_no"));


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
    }*/


}