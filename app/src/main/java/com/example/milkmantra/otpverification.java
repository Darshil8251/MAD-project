package com.example.milkmantra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.milkmantra.customer.home_customer;
import com.example.milkmantra.model.Custom_Provider_Home;
import com.example.milkmantra.provider.EndPoints;
import com.example.milkmantra.provider.provider_home;
import com.example.milkmantra.provider.provider_verification_page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class otpverification extends AppCompatActivity {

    Toolbar toolbar;

    Button submit,ResendOTP;

    EditText txtotp;

    // it for take parameter from mainActivity
    String IntentExtraphoneNumber,IntentExtraOTP;

    // it for put otp in edittext
    String EdittextgetOTP;
    static ConnectionDetector cd;
    static Boolean isInternetPresent = false;
    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);




        txtotp=findViewById(R.id.OTP);
        toolbar=findViewById(R.id.OTToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle("OTP Verification");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            IntentExtraOTP = (String) b.get("OTP");
            IntentExtraphoneNumber = (String) b.get("NUMBER");
        } else {
            IntentExtraOTP = "0";
            IntentExtraphoneNumber="";
        }


        otp=IntentExtraOTP;




        // it is call when click on resend otp

        ResendOTP=findViewById(R.id.ResendOTP);

        ResendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SmsManager smsManager = SmsManager.getDefault();
                otp=generateAndSendOTP();
                smsManager.sendTextMessage(IntentExtraphoneNumber, null, otp, null, null);

            }


            private String generateAndSendOTP() {
                // Generate a random 6-digit OTP
                Random random = new Random();
                int otp = 100000 + random.nextInt(900000);
                return String.valueOf(otp);

            }
        });


        // it call validation time

        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EdittextgetOTP=txtotp.getText().toString();
                if(EdittextgetOTP.length()<6){
                    Toast.makeText(otpverification.this,"Please enter correct OTP",Toast.LENGTH_LONG).show();
                }
                else{
                    if(!EdittextgetOTP.trim().equals(otp.trim())){
                        Toast.makeText(otpverification.this,"You enter Wrong OTP",Toast.LENGTH_LONG).show();

                    }
                    else{
                        MyPreferenceManager ObjmyPreferenceManager=new MyPreferenceManager(otpverification.this);
                        ObjmyPreferenceManager.storePhnumber(IntentExtraphoneNumber);
                        ObjmyPreferenceManager.storeFlage("0");

                        userIdentification();


                       /* Intent intent=new Intent(getApplicationContext(),asking_option.class);
                        startActivity(intent);*/
                    }
                }


            }
        });
    }

    private void userIdentification() {




            //CAll If Internet is available
            cd = new ConnectionDetector(this);
            isInternetPresent = cd.isConnectingToInternet();

            // check for Internet status
            if (isInternetPresent) {

                // Toast.makeText(context, "Name " + name + "Email " + email + " phonenumber " + phonenumber + " maincity " + maincity, Toast.LENGTH_SHORT).show();
                StringRequest strReq = new StringRequest(Request.Method.POST,
                        EndPoints.USER_IDENTIFICATION, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //UPDATE PROPETIES

                        try {

                            JSONObject obj = new JSONObject(response);
                            //txtmessage.setText(obj.getString("message"));
                            // check for error flag
                                if(obj.getString("user_type").equals("0")){
                                    Intent intent=new Intent(otpverification.this,asking_option.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else if (obj.getString("user_type").equals("1")){
                                    JSONObject provider=obj.getJSONObject("user");



                                    // if it is provider then set all the detail in myprefrence and call provider_home screen
                                    if(provider.getString("provider_is_active").equals("1")){

                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_id(provider.getString("provider_id"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Name(provider.getString("provider_name"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Phone(provider.getString("provider_phone_number"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Pincode(provider.getString("provider_pincode"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Address(provider.getString("provider_address"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Vacation_Mode(provider.getString("provider_vacation_mode"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Qr_Code(provider.getString("provider_qr_code"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Is_Active(provider.getString("provider_is_active"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Time_Stamp(provider.getString("provider_time_stamp"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().storeFlage("1");


                                        Intent intent=new Intent(otpverification.this, provider_home.class);
                                        startActivity(intent);
                                        finish();


                                    }
                                    else if(provider.getString("provider_is_active").equals("0")){



                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_id(provider.getString("provider_id"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Name(provider.getString("provider_name"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Phone(provider.getString("provider_phone_number"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Pincode(provider.getString("provider_pincode"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Address(provider.getString("provider_address"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Vacation_Mode(provider.getString("provider_vacation_mode"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Qr_Code(provider.getString("provider_qr_code"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Is_Active(provider.getString("provider_is_active"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_Time_Stamp(provider.getString("provider_time_stamp"));
                                        MyApplication_OnlineTransfer.getInstance().getPrefManager().storeFlage("2");


                                        Intent intent=new Intent(otpverification.this, provider_verification_page.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                } else if (obj.getString("user_type").equals("2")) {
                                    JSONObject customer=obj.getJSONObject("user");

                                    MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Id(customer.getString("customer_id"));
                                    MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Name(customer.getString("customer_name"));
                                    MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Phone_Number(customer.getString("customer_phone_number"));
                                    MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_id(customer.getString("provider_id"));
                                    MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Address(customer.getString("customer_address"));
                                    MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Pincode(customer.getString("customer_pincode"));
                                    MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Is_Active(customer.getString("customer_is_active"));
                                    MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Unique_No(customer.getString("customer_unique_no"));
                                    MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Timestmap(customer.getString("customer_timestamp"));
                                    MyApplication_OnlineTransfer.getInstance().getPrefManager().storeFlage("3");


                                    Intent intent=new Intent(otpverification.this, home_customer.class);
                                    startActivity(intent);
                                    finish();
                                }

                        } catch (JSONException e)
                        {
                            Toast.makeText(otpverification.this, "Check Internet Connection.#2" + e.toString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        NetworkResponse networkResponse = error.networkResponse;
                        Toast.makeText(otpverification.this, "Check Internet Connection.#3" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();


                        // here we have take provider_id from sharepreference
                        params.put("phone_number",MyApplication_OnlineTransfer.getInstance().getPrefManager().get_PhoneNumber());
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
                Toast.makeText(otpverification.this, "Check Internet connection & Retry.", Toast.LENGTH_SHORT).show();
            }

        }


    }
