package com.example.adminpureplate;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.adminpureplate.databinding.ActivityOutForDeliveryBinding;

import java.util.ArrayList;
import java.util.Arrays;

import Adapter.deliveryAdapter;
import Models.customerModels;

public class OutForDeliveryActivity extends AppCompatActivity {
    ActivityOutForDeliveryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityOutForDeliveryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       binding.backButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });

        ArrayList<String> customerNames = new ArrayList<>(Arrays.asList("Sam","Karna","Rohan","shama","kritika"));
        ArrayList<String> moneyStatus = new ArrayList<>(Arrays.asList("received","Not Received","Pending","Not Received","received"));

        ArrayList<customerModels> customerModelList = new ArrayList<>();
        for (int i = 0; i < customerNames.size(); i++) {
            customerModelList.add(new customerModels(customerNames.get(i), moneyStatus.get(i)));
        }

        deliveryAdapter adapter = new deliveryAdapter(this, customerModelList);
        binding.deliveryRecyclerView.setAdapter(adapter);
        binding.deliveryRecyclerView.setLayoutManager(new LinearLayoutManager(this));





    }
}