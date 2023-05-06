package com.example.milkmantra;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.milkmantra.customer.create_account_customer;
import com.example.milkmantra.provider.provider_verification_page;

public class asking_option extends AppCompatActivity {

    Button skip;
    ImageView call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asking_option);


        skip=findViewById(R.id.SkipCustomer);
        call=findViewById(R.id.CallProvider);



        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provider_start();
            }

            private void provider_start() {
                Intent intent=new Intent(asking_option.this, provider_verification_page.class);
                startActivity(intent);
            }
        });


        // if user press the skip button means user be customer take the permission
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSkipDialog();
            }
        });



    }


    private void showSkipDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.popup_dialog, null);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();

        Button cancelButton = dialogView.findViewById(R.id.cancle);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button confirmButton = dialogView.findViewById(R.id.confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform action for skipping
                dialog.dismiss();
                Intent intent=new Intent(getApplicationContext(), create_account_customer.class);
                startActivity(intent);
            }
        });

        dialog.show();
    }

}