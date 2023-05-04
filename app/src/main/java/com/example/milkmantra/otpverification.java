package com.example.milkmantra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class otpverification extends AppCompatActivity {

    Toolbar toolbar;

    Button submit;

    EditText txtotp;

    // it for take parameter from mainActivity
    String IntentExtraphoneNumber,IntentExtraOTP;

    // it for put otp in edittext
    String EdittextgetOTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        submit=findViewById(R.id.submit);

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

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EdittextgetOTP=txtotp.getText().toString();
                Toast.makeText(otpverification.this,EdittextgetOTP + " = " + IntentExtraOTP,Toast.LENGTH_LONG).show();
                if(EdittextgetOTP.length()<6){
                    Toast.makeText(otpverification.this,"Please enter correct OTP",Toast.LENGTH_LONG).show();
                }
                else{
                    if(!EdittextgetOTP.trim().equals(IntentExtraOTP.trim())){
                        Toast.makeText(otpverification.this,"You enter Wrong OTP",Toast.LENGTH_LONG).show();

                    }
                    else{
                        MyPreferenceManager ObjmyPreferenceManager=new MyPreferenceManager(otpverification.this);
                        ObjmyPreferenceManager.storePhnumber(IntentExtraphoneNumber);
                        ObjmyPreferenceManager.storeFlage("0");

                        Toast.makeText(otpverification.this, "Value" + ObjmyPreferenceManager.get_PhoneNumber(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),asking_option.class);
                        startActivity(intent);
                    }
                }


            }
        });
    }
}