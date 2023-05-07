package com.example.milkmantra.customer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.milkmantra.ConnectionDetector;
import com.example.milkmantra.MyApplication_OnlineTransfer;
import com.example.milkmantra.R;
import com.example.milkmantra.model.Custom_Provider_Home;
import com.example.milkmantra.provider.EndPoints;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class create_account_customer extends AppCompatActivity {
    private EditText edt_customer_name,edt_customer_pincode,edt_customer_address;
    private Button register;

    private TextView text_CustomerNumber;

    Toolbar toolbar;

    static ConnectionDetector cd;
    static Boolean isInternetPresent = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_customer);


        // it is handle toolbar of create account

        toolbar=findViewById(R.id.CustomerRegistrationToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Create New Account");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        //take the value from edit text
        edt_customer_name = findViewById(R.id.CustomerName);
        text_CustomerNumber= findViewById(R.id.CustomerNumber);
        edt_customer_pincode = findViewById(R.id.CustomerPincode);
        edt_customer_address = findViewById(R.id.CustomerAddress);
        register = findViewById(R.id.CustomerRegister);

        text_CustomerNumber.setText(MyApplication_OnlineTransfer.getInstance().getPrefManager().get_PhoneNumber());


        // adding on click listener to our button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (edt_customer_name.getText().toString().isEmpty() && edt_customer_address.getText().toString().isEmpty() && edt_customer_pincode.getText().toString().isEmpty()) {
                    Toast.makeText(create_account_customer.this, "Please enter all the Detail Proper", Toast.LENGTH_SHORT).show();
                }

                // calling a method to post the data and passing our name and job.
                else {
                    createAccount(edt_customer_name.getText().toString(),edt_customer_pincode.getText().toString(), edt_customer_address.getText().toString());
                }
            }
        });


    }

    private void createAccount(String Customer_Name, String Customer_Pincode, String Customer_Address) {

            //CAll If Internet is available
            cd = new ConnectionDetector(this);
            isInternetPresent = cd.isConnectingToInternet();

            // check for Internet status
            if (isInternetPresent) {

                // Toast.makeText(context, "Name " + name + "Email " + email + " phonenumber " + phonenumber + " maincity " + maincity, Toast.LENGTH_SHORT).show();
                StringRequest strReq = new StringRequest(Request.Method.POST,
                        EndPoints.CREATE_CUSTOMER_SAVE, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //UPDATE PROPETIES

                        try {


                            JSONObject obj = new JSONObject(response);


                            // check for error flag
                            if (obj.getBoolean("error") == false){

                          JSONObject user=obj.getJSONObject("user");
                         MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Id(user.getString("customer_id"));
                         MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Name(user.getString("customer_name"));
                         MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Phone_Number(user.getString("customer_phone_number"));
                         MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Provider_id(user.getString("provider_id"));
                         MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Pincode(user.getString("customer_pincode"));
                         MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Is_Active(user.getString("customer_is_active"));
                         MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Unique_No(user.getString("customer_unique_no"));
                         MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Timestmap(user.getString("customer_timestamp"));

                         Toast.makeText(create_account_customer.this, "Your Account Created", Toast.LENGTH_SHORT).show();
                         Intent intent=new Intent(create_account_customer.this,home_customer.class);
                         startActivity(intent);
                         finish();




                            } else {
                                // error in fetching chat rooms
                                Toast.makeText(create_account_customer.this, "Check Internet Connection.#1", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e)
                        {
                            Toast.makeText(create_account_customer.this, "Check Internet Connection.#2" + e.toString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        NetworkResponse networkResponse = error.networkResponse;
                        Toast.makeText(create_account_customer.this, "Check Internet Connection.#3" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();


                        // here we have take provider_id from sharepreference
                        params.put("customer_name",Customer_Name);
                        params.put("customer_phone_number",MyApplication_OnlineTransfer.getInstance().getPrefManager().get_PhoneNumber());
                        params.put("customer_address",Customer_Address);
                        params.put("customer_pincode",Customer_Pincode);


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
                Toast.makeText(this, "Check Internet connection & Retry.", Toast.LENGTH_SHORT).show();
            }

        }





}