package com.example.milkmantra.customer;

import static android.widget.Toast.LENGTH_LONG;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import com.example.milkmantra.model.Customer_Add_Provider_model;
import com.example.milkmantra.provider.EndPoints;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class home_customer extends AppCompatActivity {
    ArrayList<Customer_Add_Provider_model> provider_selections=new ArrayList<Customer_Add_Provider_model>();
     RecyclerView recyclerView;
    Provider_Selection_Adapter adapter_fb;
     ImageView profile;
     Toolbar toolbar;
     Button provider_selection;

     TextView textCustomerId;

    ConnectionDetector cd;
    Boolean isInternetPresent=false;
    private static final int REQUEST_READ_CONTACTS_PERMISSION = 0;
    private static final int REQUEST_CONTACT = 1;
    String contactNumber="1234567890";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);

        //for fetching contacts on click event
        provider_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(pickContact, REQUEST_CONTACT);
                requestContactsPermission();
                hasContactsPermission();
            }
        });


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

        // it for customer
   

        profile=findViewById(R.id.Profile);
        recyclerView=findViewById(R.id.recyycleViewOfHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter_fb);
        findcustomer(this);
      //  Customer_Add_Provider_model customer_add_provider_mode=new Customer_Add_Provider_model("Mayank","2","3","2","0","0","0","false");
       // provider_selections.add(customer_add_provider_mode);
      /*  provider_selections.add(new Customer_Add_Provider_model("Darshil","2","3","5","0","0","0","false"));
        provider_selections.add(new Customer_Add_Provider_model("Umang","2","3","5","0","0","0","false"));
        provider_selections.add(new Customer_Add_Provider_model("Nikhil","2","3","5","0","0","0","false"));
        provider_selections.add(new Customer_Add_Provider_model("Jayesh","2","3","5","0","0","0","false"));
        provider_selections.add(new Customer_Add_Provider_model("Manoj","2","3","5","0","0","0","false"));*/
       // provider_selection_adapter=new Provider_Selection_Adapter(this,provider_selections);
       // recyclerView.setAdapter(provider_selection_adapter);


        /*notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_Notification();
            }

            private void open_Notification() {
                Intent intent=new Intent(getApplicationContext(),customer_notification.class);
                startActivity(intent);
            }
        });*/


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


                    params.put("provider_phone_number","9924343883");
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

    //fetching contact number
    private void requestContactsPermission() {
        // Request contact permission if it
        // has not been granted already
        if (!hasContactsPermission()) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS_PERMISSION);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_READ_CONTACTS_PERMISSION && grantResults.length > 0) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean hasContactsPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) return;

        if (requestCode == REQUEST_CONTACT && data != null) {
            try {

                Cursor cursor1, cursor2;

                //get data from intent
                Uri uri = data.getData();

                cursor1 = getContentResolver().query(uri, null, null, null, null);

                if (cursor1.moveToFirst()) {
                    //get contact details
                    String contactId = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts._ID));
                    String contactName = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String contactThumnail = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
                    String idResults = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    int idResultHold = Integer.parseInt(idResults);


                    if (idResultHold == 1) {
                        cursor2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                                null,
                                null
                        );
                        //a contact may have multiple phone numbers
                        while (cursor2.moveToNext()) {
                            //get phone number
                            contactNumber = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                        }
                        Toast.makeText(this, "Number :" + contactNumber, Toast.LENGTH_SHORT).show();
                        cursor2.close();
                    }
                    cursor1.close();
                }

            } catch (Exception e) {
                Toast.makeText(this, "Exception : " + e, Toast.LENGTH_SHORT).show();
            }
        }
    }



}