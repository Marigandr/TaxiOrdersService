package com.testtasks.taxiordersservice.ui;

import android.view.View;

import com.testtasks.taxiordersservice.data.order.Order;

public interface MainActivityCallback {

    void setToolbar(int titleResId, boolean backButtonEnabled);

    void setMenu(boolean infoItemVisible);

    void showSnackbar(String message);

    void showLoading(boolean isVisible);

    void setRefreshButton(View.OnClickListener listener, boolean isVisible);

    void openOrderDetailsController(Order order);
}
