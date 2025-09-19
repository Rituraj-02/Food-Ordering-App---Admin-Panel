package com.example.adminpureplate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adminpureplate.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Models.User;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference database;
    ActivitySignUpBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        //initilizig firebase auth
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference("Users");

        binding.createButton.setOnClickListener(view -> {
            String restaurantName = binding.resturentName.getText().toString().trim();
            String email = binding.emailOrPhone.getText().toString().trim();
            String password = binding.password.getText().toString().trim();
            String ownerName = binding.name.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || restaurantName.isEmpty() || ownerName.isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Creating user with Firebase Auth
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignUpActivity.this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            if (firebaseUser != null) {
                                String userId = firebaseUser.getUid();

                                // Create User model and save in database
                                User user = new User(ownerName, email, password, restaurantName);
                                database.child(userId).setValue(user)
                                        .addOnCompleteListener(dbTask -> {
                                            if (dbTask.isSuccessful()) {
                                                Toast.makeText(SignUpActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                                finish();
                                            } else {
                                                Toast.makeText(SignUpActivity.this, "User created but data not saved.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        } else {
                            Toast.makeText(SignUpActivity.this, "Signup failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });

        binding.alreadyhavebutton.setOnClickListener(view -> {
            startActivity(new Intent(this, loginActivity.class));
        });

        String[] locationList = new String[]{"Jaipur", "Bhopal", "Banglore", "Delhi"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                locationList
        );
        AutoCompleteTextView autoCompleteTextView = binding.ListOfLocation;
        autoCompleteTextView.setAdapter(adapter);


    }
}