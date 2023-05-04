package com.example.milkmantra.provider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.milkmantra.Adapter.Provider_Customer_Adapter;
import com.example.milkmantra.R;
import com.example.milkmantra.model.Custom_Provider_Home;

import java.util.ArrayList;
import java.util.List;

public class provider_home extends AppCompatActivity {

    Toolbar toolbar;
    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<Custom_Provider_Home> customer_list=new ArrayList<>();
    ImageView more;

    Provider_Customer_Adapter provider_customer_adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_home);

        toolbar=findViewById(R.id.ProviderHomeToolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Home");
        }

        // handle search

        searchView=findViewById(R.id.search_view);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                fileList(s);
                return false;
            }
        });

        // it for customer
        recyclerView=findViewById(R.id.ProviderHomeRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Custom_Provider_Home custom_provider_home=new Custom_Provider_Home("1","Darshil Kanzariya");
        customer_list.add(custom_provider_home);
        customer_list.add(new Custom_Provider_Home("2","Mayank Patel"));
        customer_list.add(new Custom_Provider_Home("3","Sumit Dabhi"));
        customer_list.add(new Custom_Provider_Home("4","Anil Suthariya"));
        provider_customer_adapter=new Provider_Customer_Adapter(this,customer_list);
        recyclerView.setAdapter(provider_customer_adapter);


        // it for more

        more=findViewById(R.id.More);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),provider_more.class);
                startActivity(intent);
            }
        });

    }

    private void fileList(String s) {
        List<Custom_Provider_Home> fileterList=new ArrayList<>();
        for(Custom_Provider_Home item:customer_list){
            if(item.getNo().contains(s)){
                fileterList.add(item);
            }

        }
        if(fileterList.isEmpty()){
            Toast.makeText(this,"No Data found",Toast.LENGTH_LONG).show();
        }
        else{

            provider_customer_adapter.setFilteredList(fileterList,s);
            // recyclerView.scrollToPosition(0);
        }


    }
}