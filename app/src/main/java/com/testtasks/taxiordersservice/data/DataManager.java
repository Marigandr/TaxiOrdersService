package com.testtasks.taxiordersservice.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.testtasks.taxiordersservice.Application;
import com.testtasks.taxiordersservice.data.order.Order;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DataManager implements DataManagerI {

    @Inject
    APIService apiService;
    @Inject
    Context context;
    @Inject
    PreferenceManager preferenceManager;

    @Inject
    public DataManager() {
        Application.getComponent().inject(this);
    }

    @Override
    public Observable<List<Order>> getOrders() {
        return apiService.getOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ResponseBody> getVehicleImage(String imageName) {
        return apiService.getVehicleImage(imageName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void saveVehicleImage(ResponseBody responseBody, String imageName) throws IOException {
        InputStream is = responseBody.byteStream();

        File file = new File(context.getFilesDir(), imageName);
        FileOutputStream fos = new FileOutputStream(file);
        int read;
        byte[] buffer = new byte[32768];
        while ((read = is.read(buffer)) > 0) {
            fos.write(buffer, 0, read);
        }
        fos.close();
        is.close();

        HashMap<String, Long> map = preferenceManager.getPhotoLifetimeMap();
        map.put(imageName, System.currentTimeMillis());
        preferenceManager.setPhotoLifetimeMap(map);
    }

    @Override
    public Bitmap getVehicleImageBitmap(String imageName) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        String path = context.getFilesDir() + "/" + imageName;

        return BitmapFactory.decodeFile(path, options);
    }

    @Override
    public PreferenceManager getPreferenceManager() {
        return preferenceManager;
    }

    @Override
    public Context getAppContext() {
        return context;
    }
}
