package com.example.adminpureplate;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adminpureplate.databinding.ActivityAdminProfileBinding;


public class adminProfile extends AppCompatActivity {
    ActivityAdminProfileBinding binding;
    private boolean isEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminProfileBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        binding.name.setEnabled(false);
        binding.address.setEnabled(false);
        binding.email.setEnabled(false);
        binding.phone.setEnabled(false);
        binding.password.setEnabled(false);

        boolean setEnabled=false;

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        binding.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEnabled = !isEnabled;
                binding.name.setEnabled(isEnabled);
                binding.address.setEnabled(isEnabled);
                binding.email.setEnabled(isEnabled);
                binding.phone.setEnabled(isEnabled);
                binding.password.setEnabled(isEnabled);
                if(isEnabled){
                    binding.name.requestFocus();
                }
            }
        });


    }
}