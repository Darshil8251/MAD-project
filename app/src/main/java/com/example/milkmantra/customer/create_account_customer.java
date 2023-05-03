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
    private EditText customer_name,customer_phone,customer_pincode,customer_address;
    private Button register;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_customer);


        toolbar=findViewById(R.id.CustomerRegistrationToolbar);

        customer_name = findViewById(R.id.Name);
        customer_phone = findViewById(R.id.Phone_Number);
        customer_pincode = findViewById(R.id.Pincode);
        customer_address = findViewById(R.id.Address);
        register = findViewById(R.id.register);

        // adding on click listener to our button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (customer_name.getText().toString().isEmpty() && customer_address.getText().toString().isEmpty()) {
                    Toast.makeText(create_account_customer.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                postDataUsingVolley(customer_name.getText().toString(),customer_phone.getText().toString(),customer_pincode.getText().toString(),customer_address.getText().toString());
            }
        });
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Create New Account");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


    }

    // Fetch the stored data in onResume() Because this is what will be called when the app opens again
    @Override
    protected void onResume() {
        super.onResume();
        // Fetching the stored data from the SharedPreference
//        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        String cname = sh.getString("name", "");
//        int contact = sh.getInt("contact", 0000000000);
//        String caddress = sh.getString("Address", "");
//        int cpincode = sh.getInt("pincode", 000000);
//
//        // Setting the fetched data in the EditTexts
//        customer_name.setText(cname);
//        customer_phone.setText(String.valueOf(contact));
//        customer_pincode.setText(String.valueOf(cpincode));
//        customer_address.setText(caddress);

    }

    // Store the data in the SharedPreference in the onPause() method
    // When the user closes the application onPause() will be called and data will be stored
    @Override
    protected void onPause() {
        super.onPause();
        // Creating a shared pref object with a file name "MySharedPref" in private mode
//        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//        // write all the data entered by the user in SharedPreference and apply
//        myEdit.putString("name", customer_name.getText().toString());
//        myEdit.putInt("contact", Integer.parseInt(customer_phone.getText().toString()));
//        myEdit.putString("address", customer_address.getText().toString());
//        myEdit.putInt("pincode", Integer.parseInt(customer_pincode.getText().toString()));
//
//        myEdit.apply();
    }


    private void postDataUsingVolley(String name, String contact,String pincode,String address) {
        // url to post our data
        String url = "http://meteorrider.socialstuf.com/milkmantra/v1/index_v2.php/create_customer";

        RequestQueue queue = Volley.newRequestQueue(create_account_customer.this);


        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                customer_name.setText("");
                customer_address.setText("");
                customer_pincode.setText("");
                customer_phone.setText("");


                Toast.makeText(create_account_customer.this, "Data added to API", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),home_customer.class);
                startActivity(intent);
                try {

                    JSONObject respObj = new JSONObject(response);

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