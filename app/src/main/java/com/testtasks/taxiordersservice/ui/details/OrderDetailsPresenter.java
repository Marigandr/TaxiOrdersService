package com.testtasks.taxiordersservice.ui.details;

import android.graphics.Bitmap;

import com.testtasks.taxiordersservice.Application;
import com.testtasks.taxiordersservice.R;
import com.testtasks.taxiordersservice.ui.base.BasePresenter;

public class OrderDetailsPresenter extends BasePresenter implements OrderDetailsPresenterI {

    private OrderDetailsControllerI view;

    public void onCreate(OrderDetailsControllerI view) {
        Application.getComponent().inject(this);
        this.view = view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        view = null;
    }

    @Override
    public void getVehicleImage(String imageName, boolean isNetworkConnected) {
        if (dataManager.getPreferenceManager().getPhotoLifetimeMap().containsKey(imageName)) {
            Bitmap bitmap = dataManager.getVehicleImageBitmap(imageName);
            view.setVehiclePhoto(bitmap);
        } else {
            if (isNetworkConnected) {
                compositeDisposable.add(dataManager.getVehicleImage(imageName)
                        .doOnSubscribe(disposable -> view.showLoading(true))
                        .doAfterTerminate(() -> view.showLoading(false))
                        .subscribe(responseBody -> {
                                    dataManager.saveVehicleImage(responseBody, imageName);
                                    Bitmap bitmap = dataManager.getVehicleImageBitmap(imageName);
                                    view.setVehiclePhoto(bitmap);
                                },
                                error -> view.showMessage(R.string.get_vehicle_photo_error)));
            } else {
                view.showMessage(R.string.need_internet_for_car_photo_error);
            }
        }
    }
}
