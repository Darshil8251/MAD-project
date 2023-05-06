package com.example.milkmantra.customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.milkmantra.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class create_account_customer extends AppCompatActivity {
    private EditText edt_customer_name,edt_customer_phone,edt_customer_pincode,edt_customer_address;
    private Button register;
    Toolbar toolbar;
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
        edt_customer_name = findViewById(R.id.Name);
        edt_customer_phone = findViewById(R.id.Phone_Number);
        edt_customer_pincode = findViewById(R.id.Pincode);
        edt_customer_address = findViewById(R.id.Address);
        register = findViewById(R.id.register);

        // adding on click listener to our button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (edt_customer_name.getText().toString().isEmpty() && edt_customer_address.getText().toString().isEmpty() && edt_customer_pincode.getText().toString().isEmpty() && edt_customer_phone.getText().toString().isEmpty()) {
                    Toast.makeText(create_account_customer.this, "Please enter all the Detail Proper", Toast.LENGTH_SHORT).show();

                }

                // calling a method to post the data and passing our name and job.
                else {

                    postDataUsingVolley(edt_customer_name.getText().toString(), edt_customer_phone.getText().toString(), edt_customer_pincode.getText().toString(), edt_customer_address.getText().toString());
                }
            }
        });


    }

    // Fetch the stored data in onResume() Because this is what will be called when the app opens again
    @Override
    protected void onResume() {
        super.onResume();
        // Fetching the stored data from the SharedPreference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String cname = sh.getString("name", "");
        int contact = sh.getInt("contact", 0000000000);
        String caddress = sh.getString("Address", "");
        int cpincode = sh.getInt("pincode", 000000);


//        // Setting the fetched data in the EditTexts
        edt_customer_name.setText(cname);
        edt_customer_phone.setText(String.valueOf(contact));
        edt_customer_pincode.setText(String.valueOf(cpincode));
        edt_customer_address.setText(caddress);

    }

    // Store the data in the SharedPreference in the onPause() method
    // When the user closes the application onPause() will be called and data will be stored
    @Override
    protected void onPause() {
        super.onPause();
        // Creating a shared pref object with a file name "MySharedPref" in private mode
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("name", edt_customer_name.getText().toString());
        myEdit.putInt("contact", Integer.parseInt(edt_customer_phone.getText().toString()));
        myEdit.putString("address", edt_customer_address.getText().toString());
        myEdit.putInt("pincode", Integer.parseInt(edt_customer_pincode.getText().toString()));
        myEdit.putBoolean("flage",true);
        myEdit.apply();

    }


    private void postDataUsingVolley(String name, String contact,String pincode,String address) {
        // url to post our data
        String url = "http://meteorrider.socialstuf.com/milkmantra/v1/index_v2.php/create_customer";

        RequestQueue queue = Volley.newRequestQueue(create_account_customer.this);


        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                edt_customer_name.setText("");
                edt_customer_address.setText("");
                edt_customer_pincode.setText("");
                edt_customer_phone.setText("");


                Toast.makeText(create_account_customer.this, "Data added to API", Toast.LENGTH_SHORT).show();

                try {



                    JSONObject respObj = new JSONObject(response);
                    Intent intent=new Intent(getApplicationContext(),home_customer.class);
                    startActivity(intent);


                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//        // write all the data entered by the user in SharedPreference and apply
                    myEdit.putString("name", edt_customer_name.getText().toString());
                    myEdit.putInt("contact", Integer.parseInt(edt_customer_phone.getText().toString()));
                    myEdit.putString("address", edt_customer_address.getText().toString());
                    myEdit.putInt("pincode", Integer.parseInt(edt_customer_pincode.getText().toString()));
                    myEdit.apply();



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(create_account_customer.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("customer_name", name);
                params.put("customer_phone_number", contact);
                params.put("customer_pincode", pincode);
                params.put("customer_address", address);
                // at last we are
                // returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }

}