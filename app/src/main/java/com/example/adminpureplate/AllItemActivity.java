package com.example.adminpureplate;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.adminpureplate.databinding.ActivityAllItemBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter.MenutemAdapter;
import Models.itemModels;

public class AllItemActivity extends AppCompatActivity {

    ActivityAllItemBinding binding;
    MenutemAdapter adapter;
    ArrayList<itemModels> itemList;
    DatabaseReference menuRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        adapter = new MenutemAdapter(this, itemList);
        binding.menuRecyclerView.setAdapter(adapter);

        binding.backButton.setOnClickListener(view -> finish());

        // Firebase reference
        menuRef = FirebaseDatabase.getInstance().getReference("menu");

        // Fetch data
        menuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemList.clear();
                for (DataSnapshot itemSnap : snapshot.getChildren()) {
                    itemModels item = itemSnap.getValue(itemModels.class);
                    if (item != null) {
                        itemList.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}
