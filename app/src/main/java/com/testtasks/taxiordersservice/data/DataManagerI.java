package com.testtasks.taxiordersservice.data;

import com.testtasks.taxiordersservice.data.room.entity.Order;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface DataManagerI {

    // Rest
    Single<List<Order>> getOrdersFromServer();

    // Database
    Single<List<Order>> getOrdersFromDb();

    Completable insertOrdersInDb(List<Order> orders);

    Completable deleteOrdersFromDb();
}
