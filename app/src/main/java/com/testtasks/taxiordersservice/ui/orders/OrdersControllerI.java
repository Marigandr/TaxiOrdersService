package com.testtasks.taxiordersservice.ui.orders;

import com.testtasks.taxiordersservice.data.order.Order;
import com.testtasks.taxiordersservice.ui.base.ViewI;

import java.util.List;

public interface OrdersControllerI extends ViewI {

    void showOrdersList(List<Order> orders);

    void showLoading(boolean isVisible);
}
