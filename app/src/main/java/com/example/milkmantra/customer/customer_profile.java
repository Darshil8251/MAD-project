package com.example.milkmantra.customer;

import static android.provider.SyncStateContract.Helpers.update;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milkmantra.MainActivity;
import com.example.milkmantra.MyApplication_OnlineTransfer;
import com.example.milkmantra.R;

public class customer_profile extends AppCompatActivity {
    Button Customerlogout;
    ImageView edit_profile;

    TextView CustomerName,CustomerAddress,CustomerPincode,CustomerArea,CustomerPhoneNumber;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);



        // it is for handling toolbar

        toolbar=findViewById(R.id.ProfileToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



        // here handling all the field of customer

        CustomerName=findViewById(R.id.CustomerName);
        CustomerArea=findViewById(R.id.CustomerArea);
        CustomerPincode=findViewById(R.id.CustomerPincode);
        CustomerAddress=findViewById(R.id.CustomerAddress);
        CustomerPhoneNumber=findViewById(R.id.CustomerPhoneNumber);

        CustomerAddress.setText(MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Customer_Address());
        CustomerName.setText(MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Customer_Name());
        CustomerPincode.setText(MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Customer_Pincode());
        CustomerPhoneNumber.setText(MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Customer_Phone_Number());


        Customerlogout=findViewById(R.id.CustomerLogout);

        edit_profile=findViewById(R.id.EditeProfile);
        Customerlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
            private void logout() {
                MyApplication_OnlineTransfer.getInstance().getPrefManager().clear();
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_profile();
            }

            private void edit_profile() {
                Intent intent=new Intent(getApplicationContext(),customer_edit_profile.class);
                startActivity(intent);
            }
        });




    }
}