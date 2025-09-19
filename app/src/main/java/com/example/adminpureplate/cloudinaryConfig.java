package com.example.adminpureplate;

import android.content.Context;

import com.cloudinary.android.MediaManager;

import java.util.HashMap;
import java.util.Map;

public class cloudinaryConfig {
    private static boolean initialized = false;

    public static void init(Context context) {
        if (initialized) return;

        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", "diqoaowv1");  // your Cloudinary cloud name
        // your API key
        // ⚠️ DO NOT ADD api_secret here

        MediaManager.init(context, config);
        initialized = true;
    }
}
