package com.testtasks.taxiordersservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.testtasks.taxiordersservice.Application;
import com.testtasks.taxiordersservice.data.DataManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class PhotoCacheService extends Service {

    @Inject
    DataManager dataManager;

    private final int CLEAR_INTERVAL = 60 * 1000;
    private final int PHOTO_LIFETIME = 10 * 60 * 1000;
    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Application.getComponent().inject(this);
        timer = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                HashMap<String, Long> photoLifetimeMap = dataManager.getPreferenceManager().getPhotoLifetimeMap();
                HashMap<String, Long> updatedMap = new HashMap<>();

                if (!photoLifetimeMap.isEmpty()){
                    for (Map.Entry<String, Long> entry : photoLifetimeMap.entrySet()) {
                        String imageName = entry.getKey();
                        Long downloadTime = entry.getValue();

                        if (System.currentTimeMillis() - downloadTime >= PHOTO_LIFETIME){
                            boolean isFileDeleted = dataManager.getAppContext().deleteFile(imageName);
                            if (!isFileDeleted) {
                                updatedMap.put(imageName, downloadTime);
                            }
                        } else {
                            updatedMap.put(imageName, downloadTime);
                        }
                    }

                    dataManager.getPreferenceManager().setPhotoLifetimeMap(updatedMap);
                }
            }
        }, 0, CLEAR_INTERVAL);

        return super.onStartCommand(intent, flags, startId);
    }
}
