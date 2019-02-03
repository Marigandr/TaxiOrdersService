package com.testtasks.taxiordersservice.ui.orders;

import com.testtasks.taxiordersservice.Application;
import com.testtasks.taxiordersservice.R;
import com.testtasks.taxiordersservice.ui.base.BasePresenter;

import java.util.Collections;

public class OrdersPresenter extends BasePresenter implements OrdersPresenterI {

    private OrdersControllerI view;

    public void onCreate(OrdersControllerI view) {
        Application.getComponent().inject(this);
        this.view = view;
    }

    @Override
    public void getOrders() {
        compositeDisposable.add(dataManager.getOrders()
                .doOnSubscribe(disposable -> view.showLoading(true))
                .doAfterTerminate(() -> view.showLoading(false))
                .subscribe(orders -> {
                            Collections.sort(orders, (o1, o2) -> o2.getOrderTime().compareTo(o1.getOrderTime()));
                            view.showOrdersList(orders);
                        },
                        error -> view.showMessage(R.string.get_orders_error)));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        view = null;
    }
}
