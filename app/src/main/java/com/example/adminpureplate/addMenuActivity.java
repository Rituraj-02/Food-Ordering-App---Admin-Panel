package com.example.adminpureplate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.UploadCallback;
import com.example.adminpureplate.databinding.ActivityAddMenuBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class addMenuActivity extends AppCompatActivity {

    ActivityAddMenuBinding binding;

    private String foodName;
    private String foodPrice;
    private String foodDescription;
    private String foodIngredient;
    private Uri foodImageUri = null;

    private FirebaseAuth auth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Cloudinary once here
        cloudinaryConfig.init(this);

        binding = ActivityAddMenuBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.addItemButton.setOnClickListener(view -> {
            // Get data from input fields
            foodName = binding.foodName.getText().toString().trim();
            foodPrice = binding.foodPrice.getText().toString().trim();
            foodDescription = binding.description.getText().toString().trim();
            foodIngredient = binding.ingredients.getText().toString().trim();

            if (!(foodName.isBlank() || foodPrice.isBlank() || foodDescription.isBlank() || foodIngredient.isBlank())) {
                if (foodImageUri != null) {
                    // Upload image to Cloudinary using unsigned preset
                    MediaManager.get().upload(foodImageUri)
                            .unsigned("unsigned_preset") // <-- Use your unsigned preset name here
                            .callback(new UploadCallback() {
                                @Override
                                public void onStart(String requestId) {
                                    Toast.makeText(addMenuActivity.this, "Uploading image...", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onProgress(String requestId, long bytes, long totalBytes) {
                                    // Optional: show progress
                                }

                                @Override
                                public void onSuccess(String requestId, Map resultData) {
                                    // Image uploaded successfully, get the URL
                                    String imageUrl = (String) resultData.get("secure_url");
                                    saveMenuItemToDatabase(imageUrl);
                                }

                                @Override
                                public void onError(String requestId, com.cloudinary.android.callback.ErrorInfo error) {
                                    Toast.makeText(addMenuActivity.this, "Upload failed: " + error.getDescription(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onReschedule(String requestId, com.cloudinary.android.callback.ErrorInfo error) {
                                    // Handle reschedule if needed
                                }
                            })
                            .dispatch();
                } else {
                    // No image selected, save data without image URL
                    saveMenuItemToDatabase(null);
                }
            } else {
                Toast.makeText(addMenuActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
        });

        binding.selectImage.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 100);
        });

        binding.backButton.setOnClickListener(view -> finish());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            foodImageUri = data.getData();
            binding.selectedImage.setImageURI(foodImageUri);
        }
    }

    private void saveMenuItemToDatabase(String imageUrl) {
        DatabaseReference menuRef = database.getReference("menu");
        String newItemKey = menuRef.push().getKey();

        if (newItemKey == null) {
            Toast.makeText(this, "Failed to generate key", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> menuItem = new HashMap<>();
        menuItem.put("name", foodName);
        menuItem.put("price", foodPrice);
        menuItem.put("description", foodDescription);
        menuItem.put("ingredients", foodIngredient);
        menuItem.put("imageUrl", imageUrl);

        menuRef.child(newItemKey).setValue(menuItem)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(addMenuActivity.this, "Item added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(addMenuActivity.this, "Failed to add item: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}
