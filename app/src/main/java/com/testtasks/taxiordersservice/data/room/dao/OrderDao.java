package com.testtasks.taxiordersservice.data.room.dao;

import com.testtasks.taxiordersservice.data.room.entity.Order;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM `Order`")
    Single<List<Order>> getAll();

    @Insert
    void insertAll(List<Order> orders);

    @Query("DELETE FROM `Order`")
    void deleteAll();
}
