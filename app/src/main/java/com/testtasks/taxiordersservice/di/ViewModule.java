package com.testtasks.taxiordersservice.di;

import com.testtasks.taxiordersservice.ui.details.OrderDetailsPresenter;
import com.testtasks.taxiordersservice.ui.orders.OrdersPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    @Singleton
    OrdersPresenter provideOrdersPresenter() {
        return new OrdersPresenter();
    }

    @Provides
    @Singleton
    OrderDetailsPresenter provideOrderDetailsPresenter() {
        return new OrderDetailsPresenter();
    }
}
