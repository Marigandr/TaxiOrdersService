package com.testtasks.taxiordersservice.data;

import com.testtasks.taxiordersservice.data.room.entity.Order;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface APIService {

    @GET("orders.json")
    Single<List<Order>> getOrders();
}
