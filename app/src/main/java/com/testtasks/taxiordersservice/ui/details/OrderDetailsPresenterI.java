package com.testtasks.taxiordersservice.ui.details;

import com.testtasks.taxiordersservice.ui.base.PresenterI;

public interface OrderDetailsPresenterI extends PresenterI {

    void getVehicleImage(String imageName, boolean isNetworkConnected);
}
