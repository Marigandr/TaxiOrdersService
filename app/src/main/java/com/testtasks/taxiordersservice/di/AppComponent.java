package com.testtasks.taxiordersservice.di;

import com.testtasks.taxiordersservice.data.DataManager;
import com.testtasks.taxiordersservice.ui.MainActivity;
import com.testtasks.taxiordersservice.ui.base.BasePresenter;
import com.testtasks.taxiordersservice.ui.orders.OrdersController;
import com.testtasks.taxiordersservice.ui.orders.OrdersPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(BasePresenter basePresenter);
    void inject(DataManager dataManager);

    void inject(OrdersController ordersController);
    void inject(OrdersPresenter ordersPresenter);
}
