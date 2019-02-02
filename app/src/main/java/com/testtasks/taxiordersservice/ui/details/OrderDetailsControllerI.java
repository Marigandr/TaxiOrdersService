package com.testtasks.taxiordersservice.ui.details;

import android.graphics.Bitmap;

import com.testtasks.taxiordersservice.data.order.Order;
import com.testtasks.taxiordersservice.ui.base.ViewI;

public interface OrderDetailsControllerI extends ViewI {

    void setOrder(Order order);

    void setVehiclePhoto(Bitmap bitmap);

    void showLoading(boolean isVisible);
}
