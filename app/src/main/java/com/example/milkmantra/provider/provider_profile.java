package com.example.milkmantra.provider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milkmantra.MyApplication_OnlineTransfer;
import com.example.milkmantra.R;

public class provider_profile extends AppCompatActivity {

    Toolbar toolbar;
    ImageView home;
    TextView ProviderName,ProviderAddress,ProviderPhoneNumber,ProviderArea,ProviderPincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profile);


        // it for handling toolbar
        toolbar=findViewById(R.id.ProviderProfile);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            // it is for profile field
            ProviderName=findViewById(R.id.ProviderName);
            ProviderAddress=findViewById(R.id.ProviderAddress);
            ProviderArea=findViewById(R.id.ProviderArea);
            ProviderPhoneNumber=findViewById(R.id.ProviderPhoneNumber);
            ProviderPincode=findViewById(R.id.ProviderPincode);


            Toast.makeText(this, MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Provider_Pincode(), Toast.LENGTH_SHORT).show();
            ProviderName.setText(MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Provider_Name());
            ProviderPincode.setText(MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Provider_Pincode());
            ProviderArea.setText(MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Provider_Area());
            ProviderPhoneNumber.setText(MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Provider_Phone_Number());
            ProviderAddress.setText(MyApplication_OnlineTransfer.getInstance().getPrefManager().get_Provider_Address());



            // handle home
            home=findViewById(R.id.home);
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getApplicationContext(),provider_home.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}