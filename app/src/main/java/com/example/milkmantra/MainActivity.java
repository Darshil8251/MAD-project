package com.example.milkmantra;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int SEND_SMS_PERMISSION_CODE =100;
    Button sendotp;
    EditText number;
    String no,otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        number=findViewById(R.id.Number);

        sendotp=findViewById(R.id.sendotp);
        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no = number.getText().toString();


                    if(no==null){
                        Toast.makeText(MainActivity.this,"Given null number",Toast.LENGTH_LONG).show();
                    }
                SendSMS(no);
                    Intent intent=new Intent(getApplicationContext(),otpverification.class);
                    intent.putExtra("NUMBER",no);
                    intent.putExtra("OTP",otp);
                    startActivity(intent);

            }
        });
    }


    private String generateAndSendOTP() {
        // Generate a random 6-digit OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
         return String.valueOf(otp);

    }
    private void SendSMS(String no) {

        checkPermission(Manifest.permission.SEND_SMS, SEND_SMS_PERMISSION_CODE);
    }

    public void checkPermission(String sendSms, int sendSmsPermissionCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, sendSms) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { sendSms }, sendSmsPermissionCode);
        }
        else {
            Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
            SmsManager smsManager = SmsManager.getDefault();
            otp=generateAndSendOTP();
            smsManager.sendTextMessage(no, null, otp, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == SEND_SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "SMS Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "SMS Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


}





