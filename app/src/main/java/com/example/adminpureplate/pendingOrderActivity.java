package com.example.adminpureplate;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.adminpureplate.databinding.ActivityPendingOrderBinding;

import java.util.ArrayList;
import java.util.Arrays;

import Adapter.PendingOrderAdapter;
import Models.pendingOrderModels;

public class pendingOrderActivity extends AppCompatActivity {
    ActivityPendingOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPendingOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        ArrayList<String> orderCustomerNames = new ArrayList<>(Arrays.asList("Sam","Karna","Rohan","shama","kritika"));
        ArrayList<String> orderQuantity = new ArrayList<>(Arrays.asList("4","2","3","1","5"));
        ArrayList<Integer> orderFoodImages = new ArrayList<>(Arrays.asList(R.drawable.menu1,R.drawable.menu2,R.drawable.menu3,R.drawable.menu4,R.drawable.menu5));
        ArrayList<pendingOrderModels> pendingOrderModels = new ArrayList<>();
        for (int i = 0; i < orderCustomerNames.size(); i++) {
            pendingOrderModels.add(new pendingOrderModels(orderCustomerNames.get(i), orderQuantity.get(i), orderFoodImages.get(i)));
        }
        PendingOrderAdapter adapter = new PendingOrderAdapter(this, pendingOrderModels);
        binding.pendingOrderRecyclerView.setAdapter(adapter);
        binding.pendingOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}