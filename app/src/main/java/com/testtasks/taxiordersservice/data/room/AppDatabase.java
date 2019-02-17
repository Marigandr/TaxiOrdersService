package com.testtasks.taxiordersservice.data.room;

import com.testtasks.taxiordersservice.data.room.dao.OrderDao;
import com.testtasks.taxiordersservice.data.room.entity.Order;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Order.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract OrderDao orderDao();

}
