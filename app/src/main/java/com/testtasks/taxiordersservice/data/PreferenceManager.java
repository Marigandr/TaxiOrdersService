package com.testtasks.taxiordersservice.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.testtasks.taxiordersservice.service.MapWrapper;

import java.util.HashMap;

public class PreferenceManager {

    private static volatile PreferenceManager instance;
    private SharedPreferences sharedPreferences;

    private final String PHOTO_LIFETIME_MAP = "PHOTO_LIFETIME_MAP";

    public PreferenceManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static PreferenceManager getInstance(Context context) {
        PreferenceManager preferenceManager = instance;
        if (instance == null) {
            synchronized (PreferenceManager.class) {
                preferenceManager = instance;
                if (preferenceManager == null) {
                    preferenceManager = new PreferenceManager(context);
                    instance = preferenceManager;
                }
            }
        }
        return preferenceManager;
    }

    public HashMap<String, Long> getPhotoLifetimeMap() {
        String wrapperStr = sharedPreferences.getString(PHOTO_LIFETIME_MAP, "");
        if (wrapperStr != null && !wrapperStr.isEmpty()){
            Gson gson = new Gson();
            MapWrapper wrapper = gson.fromJson(wrapperStr, MapWrapper.class);
            return wrapper.getPhotoLifetimeMap();
        } else {
            return new HashMap<>();
        }
    }

    public void setPhotoLifetimeMap(HashMap<String, Long> map) {
        Gson gson = new Gson();
        MapWrapper wrapper = new MapWrapper();
        wrapper.setPhotoLifetimeMap(map);
        String serializedMap = gson.toJson(wrapper);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PHOTO_LIFETIME_MAP, serializedMap);
        editor.apply();
    }
}
