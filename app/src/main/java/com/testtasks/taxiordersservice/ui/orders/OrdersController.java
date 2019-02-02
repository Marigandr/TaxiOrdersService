package com.testtasks.taxiordersservice.ui.orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testtasks.taxiordersservice.Application;
import com.testtasks.taxiordersservice.R;
import com.testtasks.taxiordersservice.data.order.Order;
import com.testtasks.taxiordersservice.ui.MainActivityCallback;
import com.testtasks.taxiordersservice.ui.base.BaseController;
import com.testtasks.taxiordersservice.ui.base.PresenterI;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersController extends BaseController implements OrdersControllerI {

    @BindView(R.id.orders)
    RecyclerView orders;
    @BindView(R.id.refresh_button)
    AppCompatButton refreshButton;

    @Inject
    OrdersPresenter presenter;

    private OrdersAdapter ordersAdapter;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_orders, container, false);
        Application.getComponent().inject(this);
        ButterKnife.bind(this, view);

        initView();
        getOrders();

        return view;
    }

    @Override
    public void initView() {
        presenter.onCreate(this);
        mainActivityCallback = (MainActivityCallback) getActivity();
        mainActivityCallback.setToolbar(R.string.orders, false);
        mainActivityCallback.setMenu(true);
        orders.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        refreshButtonListener = v -> getOrders();
    }

    @Override
    public PresenterI getPresenter() {
        return presenter;
    }

    @Override
    public void showOrdersList(List<Order> ordersList) {
        ordersAdapter = new OrdersAdapter(ordersList, getApplicationContext(), mainActivityCallback);
        orders.setAdapter(ordersAdapter);
    }

    @Override
    public void showLoading(boolean isVisible) {
        mainActivityCallback.showLoading(isVisible);
    }

    private void getOrders(){
        if(isNetworkConnected()){
            mainActivityCallback.setRefreshButton(null, false);
            presenter.getOrders();
        } else {
            mainActivityCallback.setRefreshButton(refreshButtonListener, true);
            showMessage(R.string.network_absent_error);
        }
    }
}
