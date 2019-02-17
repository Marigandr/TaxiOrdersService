package com.testtasks.taxiordersservice.ui.orders;

import com.testtasks.taxiordersservice.Application;
import com.testtasks.taxiordersservice.R;
import com.testtasks.taxiordersservice.data.room.entity.Order;
import com.testtasks.taxiordersservice.ui.base.BasePresenter;

import java.util.Collections;
import java.util.List;

public class OrdersPresenter extends BasePresenter implements OrdersPresenterI {

    private OrdersControllerI view;

    public void onCreate(OrdersControllerI view) {
        Application.getComponent().inject(this);
        this.view = view;
    }

    @Override
    public void getOrdersFromServer() {
        compositeDisposable.add(dataManager.getOrdersFromServer()
                .doOnSubscribe(disposable -> view.showLoading(true))
                .doAfterTerminate(() -> view.showLoading(false))
                .subscribe(orders -> {
                            view.showLoading(false);
                            Collections.sort(orders, (o1, o2) -> o2.getOrderTime().compareTo(o1.getOrderTime()));
                            view.showOrdersList(orders);
                            deleteAndInsertOrdersInDb(orders);
                        },
                        error -> getOrdersFromDb()));
    }

    @Override
    public void getOrdersFromDb() {
        compositeDisposable.add(dataManager.getOrdersFromDb()
                .subscribe(orders -> {
                            if (orders.isEmpty()) {
                                view.showRefreshButton(true);
                            } else {
                                Collections.sort(orders, (o1, o2) -> o2.getOrderTime().compareTo(o1.getOrderTime()));
                                view.showOrdersList(orders);
                                view.showRefreshButton(false);
                                view.showLoading(false);
                            }
                            view.showMessage(R.string.network_absent_error);
                        },
                        error -> {
                            view.showRefreshButton(true);
                            view.showMessage(R.string.network_absent_error);
                        }));
    }

    private void deleteAndInsertOrdersInDb(List<Order> orders) {
        compositeDisposable.add(dataManager.deleteOrdersFromDb().subscribe(() -> insertOrdersInDb(orders)));
    }

    private void insertOrdersInDb(List<Order> orders) {
        compositeDisposable.add(dataManager.insertOrdersInDb(orders).subscribe());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        view = null;
    }
}
