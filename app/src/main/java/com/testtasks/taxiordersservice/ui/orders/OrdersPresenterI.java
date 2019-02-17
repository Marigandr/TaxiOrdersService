package com.testtasks.taxiordersservice.ui.orders;

import com.testtasks.taxiordersservice.ui.base.PresenterI;

public interface OrdersPresenterI extends PresenterI {

    void getOrdersFromDb();

    void getOrdersFromServer();
}
