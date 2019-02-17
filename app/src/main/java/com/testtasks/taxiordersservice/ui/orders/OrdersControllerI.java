package com.testtasks.taxiordersservice.ui.orders;

import com.testtasks.taxiordersservice.data.room.entity.Order;
import com.testtasks.taxiordersservice.ui.base.ViewI;

import java.util.List;

public interface OrdersControllerI extends ViewI {

    void showOrdersList(List<Order> orders);

    void showLoading(boolean isVisible);

    void showRefreshButton(boolean isVisible);
}
