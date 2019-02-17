package com.testtasks.taxiordersservice.ui.details;

import com.testtasks.taxiordersservice.data.room.entity.Order;
import com.testtasks.taxiordersservice.ui.base.ViewI;

public interface OrderDetailsControllerI extends ViewI {

    void setOrder(Order order);
}
