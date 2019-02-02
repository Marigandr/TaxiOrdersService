package com.testtasks.taxiordersservice.data;

import com.testtasks.taxiordersservice.data.order.Order;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("orders.json")
    Observable<List<Order>> getOrders();

    @GET("images/{imageName}")
    Observable<ResponseBody> getVehicleImage(@Path("imageName") String imageName);
}
