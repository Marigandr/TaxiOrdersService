package com.testtasks.taxiordersservice.data;

import android.content.Context;
import android.graphics.Bitmap;

import com.testtasks.taxiordersservice.data.order.Order;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface DataManagerI {

    Observable<List<Order>> getOrders();

    Observable<ResponseBody> getVehicleImage(String imageName);

    void saveVehicleImage(ResponseBody responseBody, String imageName) throws IOException;

    Bitmap getVehicleImageBitmap(String imageName);

    PreferenceManager getPreferenceManager();

    Context getAppContext();
}
