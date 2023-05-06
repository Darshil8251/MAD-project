package com.example.milkmantra.provider;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.milkmantra.MyPreferenceManager;
import com.example.milkmantra.R;
import com.example.milkmantra.asking_option;
import com.example.milkmantra.model.Customer_Add_Provider_model;
import com.example.milkmantra.otpverification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class provider_verification_page extends AppCompatActivity {


    ImageView ProviderCall;
    ConnectionDetector cd;
    Boolean isInternetPresent=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_verification_page);


        // it call the admin
        ProviderCall =findViewById(R.id.ProviderCall);


        ProviderCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                providerValidation(provider_verification_page.this);


            }


            public  void providerValidation(Context context){
                cd = new ConnectionDetector(provider_verification_page.this);
                isInternetPresent = cd.isConnectingToInternet();

                // check for Internet status
                if (isInternetPresent) {

                    // Toast.makeText(context, "Name " + name + "Email " + email + " phonenumber " + phonenumber + " maincity " + maincity, Toast.LENGTH_SHORT).show();
                    StringRequest strReq = new StringRequest(Request.Method.POST,
                            EndPoints.ADD_PROVIDER, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //UPDATE PROPETIES

                            try {

                                JSONObject obj = new JSONObject(response);
                                //txtmessage.setText(obj.getString("message"));



                                // Here store the data into sharePreference and set flag 3 for  Provider
                                if (obj.getBoolean("error") == false) {
                                    JSONObject user = (JSONObject) obj.get("user");
                                    if (user.getString("provider_id")!="0") {
                                        MyPreferenceManager ObjmyPreferenceManager = new MyPreferenceManager(provider_verification_page.this);
                                        ObjmyPreferenceManager.storeFlage("3");
                                        ObjmyPreferenceManager.set_Provider_id((String) user.getString("provider_id"));
                                        ObjmyPreferenceManager.set_Provider_Name( user.getString("provider_name"));
                                        ObjmyPreferenceManager.set_Provider_Phone( user.getString("provider_phone_number"));
                                        ObjmyPreferenceManager.set_Provider_Pincode( user.getString("provider_pincode"));
                                        ObjmyPreferenceManager.set_Provider_Address(user.getString("provider_address"));
                                        ObjmyPreferenceManager.set_Provider_Vacation_Mode(user.getString("provider_vacation_mode"));
                                        ObjmyPreferenceManager.set_Provider_Qr_Code( user.getString("provider_qr_code"));
                                        ObjmyPreferenceManager.set_Provider_Is_Active( user.getString("provider_is_active"));
                                        ObjmyPreferenceManager.set_Provider_Time_Stamp( user.getString("provider_time_stamp"));
                                        Toast.makeText(context, "Your Information Added SuccessFully", Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(provider_verification_page.this,provider_home.class);
                                        startActivity(intent);
                                        finish();

                                    }
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

                            params.put("provider_name","Anil Kumar");
                            params.put("provider_phone_number","85679696112");
                            params.put("provider_pincode","364710");
                            params.put("provider_address","America");
                            params.put("provider_vacation_mode","0");
                            params.put("provider_qr_code","123456@upi");
                            params.put("provider_remark","0");
                            params.put("provider_reserve1","0");
                            params.put("provider_reserve2","0");
                            params.put("provider_reserve3","0");


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
                    Toast.makeText(provider_verification_page.this, "Check Internet connection & Retry.", Toast.LENGTH_SHORT).show();
                }

            }

        });




    }
}