package com.testtasks.taxiordersservice.data;

import com.testtasks.taxiordersservice.Application;
import com.testtasks.taxiordersservice.data.room.AppDatabase;
import com.testtasks.taxiordersservice.data.room.entity.Order;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataManager implements DataManagerI {

    @Inject
    APIService apiService;
    @Inject
    AppDatabase appDatabase;

    @Inject
    public DataManager() {
        Application.getComponent().inject(this);
    }

    @Override
    public Single<List<Order>> getOrdersFromServer() {
        return apiService.getOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<Order>> getOrdersFromDb() {
        return appDatabase.orderDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable insertOrdersInDb(List<Order> orders) {
        return Completable.fromAction(() -> appDatabase.orderDao().insertAll(orders))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable deleteOrdersFromDb() {
        return Completable.fromAction(() -> appDatabase.orderDao().deleteAll())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
