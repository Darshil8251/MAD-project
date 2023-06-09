package com.example.milkmantra.customer;

import static android.widget.Toast.LENGTH_LONG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.milkmantra.Adapter.Provider_Selection_Adapter;
import com.example.milkmantra.ConnectionDetector;
import com.example.milkmantra.MyApplication_OnlineTransfer;
import com.example.milkmantra.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.milkmantra.asking_option;
import com.example.milkmantra.contact_list;
import com.example.milkmantra.model.Customer_Add_Provider_model;
import com.example.milkmantra.otpverification;
import com.example.milkmantra.provider.EndPoints;
import com.example.milkmantra.provider.provider_home;
import com.example.milkmantra.provider.provider_verification_page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class home_customer extends AppCompatActivity {
    ArrayList<Customer_Add_Provider_model> provider_selections=new ArrayList<Customer_Add_Provider_model>();
     RecyclerView recyclerView;
    Provider_Selection_Adapter adapter_fb;
 ImageView profile,CustomerContactcs;
 Toolbar toolbar;
 SearchView searchView;
 TextView textCustomerId;

    ConnectionDetector cd;
    Boolean isInternetPresent=false;

    String number="";
    String name="";
    static final int PICK_CONTACT=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);


        // it is for toolbar
        toolbar=findViewById(R.id.HomeToolbar);
        setSupportActionBar(toolbar);

        if(getApplicationContext()!=null){
            getSupportActionBar().setTitle("Home");
        }
        adapter_fb = new Provider_Selection_Adapter(this,new ArrayList<Customer_Add_Provider_model>());

        // here show the text Customer Id

        String text="Your Customer ID Is ";

        String CustomerId=MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Customer_Id();

        textCustomerId=findViewById(R.id.textCustomerId);

        textCustomerId.setText(text+CustomerId);

        CustomerContactcs=findViewById(R.id.CustomerContactcs);

        // here handle the search

        searchView=findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //fileList(s);

                return false;
            }
        });



        // it is handle Customer Contacts

        CustomerContactcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(home_customer.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
// requesting to the user for permission.
                    ActivityCompat.requestPermissions(home_customer.this, new String[]{Manifest.permission.READ_CONTACTS}, 100);

                }

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);

            }
        });



        // it for customer
   

        profile=findViewById(R.id.Profile);
        recyclerView=findViewById(R.id.recyycleViewOfHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter_fb);
        findcustomer(this);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile();
            }

            private void profile() {
                Intent intent=new Intent(getApplicationContext(),customer_profile.class);
                startActivity(intent);
            }
        });



        // It is for notification



    }


    @SuppressLint("Range")
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c =  managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {

                        String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                        @SuppressLint("Range") String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        if (hasPhone.equalsIgnoreCase("1")) {
                            Cursor phones = getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,
                                    null, null);
                            phones.moveToFirst();

                            String cname = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            String cNumber = phones.getString(phones.getColumnIndex("data1"));
                            number = cNumber;
                            name = cname;


                            validatecontact(number.trim().replace(" ",""));



                        }


                    }
                }
                break;
        }
    }

    private void validatecontact(String number) {


        Toast.makeText(this, number, Toast.LENGTH_SHORT).show();



        adapter_fb.clear();
        //CAll If Internet is available
        cd = new ConnectionDetector(home_customer.this);
        isInternetPresent = cd.isConnectingToInternet();

        // check for Internet status
        if (isInternetPresent) {

            // Toast.makeText(context, "Name " + name + "Email " + email + " phonenumber " + phonenumber + " maincity " + maincity, Toast.LENGTH_SHORT).show();
            StringRequest strReq = new StringRequest(Request.Method.POST,
                    EndPoints.LATEST_VALUES_NEW, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.v("eroorr","##########"+response);
                    //UPDATE PROPETIES

                    try {

                        JSONObject obj = new JSONObject(response);
                        //txtmessage.setText(obj.getString("message"));

                        // check for error flag
                        if (obj.getBoolean("error") == false) {
                            //Get random_users And show it in Top Horizontal View
                            JSONArray random_usersarray = obj.getJSONArray("random_users");

                            for (int i = 0; i < random_usersarray.length(); i++) {
                                JSONObject randomuserObj = (JSONObject) random_usersarray.get(i);
                                Customer_Add_Provider_model ObjUsr = new Customer_Add_Provider_model();
                                //id, name, email, dob, home_Address, phone_number, current_place,
                                // transplace1, transplace2, transplace3, transplace4, transplace5,
                                if (!randomuserObj.getString("customer_id").equals("0")) {
                                    ObjUsr.setCustomer_id(randomuserObj.getString("customer_id"));
                                    ObjUsr.setCustomer_vacation_mode(randomuserObj.getString("customer_vacation_mode"));

                                    ObjUsr.setCustomer_morning_cow_volume(randomuserObj.getString("customer_morning_cow_volume"));
                                    ObjUsr.setCustomer_morning_buffelo_volume(randomuserObj.getString("customer_morning_buffelo_volume"));

                                    ObjUsr.setCustomer_morning_other_volume(randomuserObj.getString("customer_morning_other_volume"));
                                    ObjUsr.setCustomer_evening_cow_volume(randomuserObj.getString("customer_evening_cow_volume"));

                                    ObjUsr.setCustomer_evening_buffelo_volume(randomuserObj.getString("customer_evening_buffelo_volume"));
                                    ObjUsr.setCustomer_evening_other_volume(randomuserObj.getString("customer_evening_other_volume"));

                                    ObjUsr.setCustomer_provider_is_active(randomuserObj.getString("customer_provider_is_active"));
                                    ObjUsr.setProvider_name(randomuserObj.getString("provider_name"));

                                    ObjUsr.setProvider_id(randomuserObj.getString("provider_id"));
                                    ObjUsr.setProvider_vacation_mode(randomuserObj.getString("provider_vacation_mode"));

                                    ObjUsr.setProvider_customer_associated(randomuserObj.getString("provider_customer_associated"));
                                    ObjUsr.setCustomer_id(randomuserObj.getString("customer_id"));

                                    adapter_fb.add(ObjUsr);
                                } else {
                                    Toast.makeText(home_customer.this, "No more data available.", Toast.LENGTH_SHORT).show();

                                }

                                // data_user.add(new Data(R.drawable.user_top, randomuserObj.getString("user_id") + "#" + randomuserObj.getString("name") + "\nLast seen :" +randomuserObj.getString("last_online") + "#" + randomuserObj.getString("institute") + "#" + randomuserObj.getString("batch") + "#" + randomuserObj.getString("branch") + "#" + randomuserObj.getString("likes") +"#"+ randomuserObj.getString("isinterested")));
                            }
                            adapter_fb.notifyDataSetChanged();
                            /////////////////////////////////////////////////////////
                        } else {
                            // error in fetching chat rooms
                            Toast.makeText(home_customer.this, "Check Internet Connection.#1", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e)
                    {
                        Toast.makeText(home_customer.this, "Check Internet Connection.#2" + e.toString(), Toast.LENGTH_SHORT).show();
                    }


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    NetworkResponse networkResponse = error.networkResponse;
                    Toast.makeText(home_customer.this, "Check Internet Connection.#3" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();


                    params.put("provider_phone_number",number);
                    params.put("customer_id",MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Customer_Id());
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
            Toast.makeText(home_customer.this, "Check Internet connection & Retry.", Toast.LENGTH_SHORT).show();
        }

    }






    private void findcustomer(Context context) {

        adapter_fb.clear();
        //CAll If Internet is available
        cd = new ConnectionDetector(context);
        isInternetPresent = cd.isConnectingToInternet();

        // check for Internet status
        if (isInternetPresent) {

            // Toast.makeText(context, "Name " + name + "Email " + email + " phonenumber " + phonenumber + " maincity " + maincity, Toast.LENGTH_SHORT).show();
            StringRequest strReq = new StringRequest(Request.Method.POST,
                    EndPoints.LATEST_VALUES_NEW, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //UPDATE PROPETIES

                    try {

                        JSONObject obj = new JSONObject(response);
                        //txtmessage.setText(obj.getString("message"));

                        // check for error flag
                        if (obj.getBoolean("error") == false) {
                            //Get random_users And show it in Top Horizontal View
                            JSONArray random_usersarray = obj.getJSONArray("random_users");

                            for (int i = 0; i < random_usersarray.length(); i++) {
                                JSONObject randomuserObj = (JSONObject) random_usersarray.get(i);
                                Customer_Add_Provider_model ObjUsr = new Customer_Add_Provider_model();
                                //id, name, email, dob, home_Address, phone_number, current_place,
                                // transplace1, transplace2, transplace3, transplace4, transplace5,
                                if (!randomuserObj.getString("customer_id").equals("0")) {
                                    ObjUsr.setCustomer_id(randomuserObj.getString("customer_id"));
                                    ObjUsr.setCustomer_vacation_mode(randomuserObj.getString("customer_vacation_mode"));

                                    ObjUsr.setCustomer_morning_cow_volume(randomuserObj.getString("customer_morning_cow_volume"));
                                    ObjUsr.setCustomer_morning_buffelo_volume(randomuserObj.getString("customer_morning_buffelo_volume"));

                                    ObjUsr.setCustomer_morning_other_volume(randomuserObj.getString("customer_morning_other_volume"));
                                    ObjUsr.setCustomer_evening_cow_volume(randomuserObj.getString("customer_evening_cow_volume"));

                                    ObjUsr.setCustomer_evening_buffelo_volume(randomuserObj.getString("customer_evening_buffelo_volume"));
                                    ObjUsr.setCustomer_evening_other_volume(randomuserObj.getString("customer_evening_other_volume"));

                                    ObjUsr.setCustomer_provider_is_active(randomuserObj.getString("customer_provider_is_active"));
                                    ObjUsr.setProvider_name(randomuserObj.getString("provider_name"));

                                    ObjUsr.setProvider_id(randomuserObj.getString("provider_id"));
                                    ObjUsr.setProvider_vacation_mode(randomuserObj.getString("provider_vacation_mode"));

                                    ObjUsr.setProvider_customer_associated(randomuserObj.getString("provider_customer_associated"));
                                    ObjUsr.setCustomer_id(randomuserObj.getString("customer_id"));

                                    adapter_fb.add(ObjUsr);
                                } else {
                                    Toast.makeText(context, "No more data available.", Toast.LENGTH_SHORT).show();

                                }

                                // data_user.add(new Data(R.drawable.user_top, randomuserObj.getString("user_id") + "#" + randomuserObj.getString("name") + "\nLast seen :" +randomuserObj.getString("last_online") + "#" + randomuserObj.getString("institute") + "#" + randomuserObj.getString("batch") + "#" + randomuserObj.getString("branch") + "#" + randomuserObj.getString("likes") +"#"+ randomuserObj.getString("isinterested")));
                            }
                            adapter_fb.notifyDataSetChanged();
                            /////////////////////////////////////////////////////////
                        } else {
                            // error in fetching chat rooms
                            Toast.makeText(context, "Check Internet Connection.#1", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e)
                    {
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


                    params.put("provider_phone_number","0");
                    params.put("customer_id",MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Customer_Id());
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