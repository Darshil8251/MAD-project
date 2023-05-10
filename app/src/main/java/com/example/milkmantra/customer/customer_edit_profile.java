package com.example.milkmantra.customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.milkmantra.MyApplication_OnlineTransfer;
import com.example.milkmantra.R;

public class customer_edit_profile extends AppCompatActivity {

    Button btn;
    Toolbar toolbar;

    EditText edtName,edtPincode,edtAddress;
    String CustomerEditName,CustomerEditPincode,CustomerEditAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_profile);

        toolbar=findViewById(R.id.EditProfileToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Edit Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btn=findViewById(R.id.Update);
        edtName=findViewById(R.id.EditName);
        edtAddress=findViewById(R.id.EditAddress);
        edtPincode=findViewById(R.id.EditPincode);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Update();
            }

            private void Update() {
                CustomerEditName=edtName.getText().toString();
                CustomerEditAddress=edtAddress.getText().toString();
                CustomerEditPincode=edtPincode.getText().toString();


               if(!CustomerEditName.isEmpty()){
                   MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Name(CustomerEditName);
               }
                if (!CustomerEditAddress.isEmpty()) {
                    MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Address(CustomerEditAddress);
                }
                if (!CustomerEditPincode.isEmpty()) {
                    MyApplication_OnlineTransfer.getInstance().getPrefManager().set_Customer_Pincode(CustomerEditPincode);
                }


                Intent intent=new Intent(getApplicationContext(),customer_profile.class);
                startActivity(intent);

            }
        });
    }
}