package com.testtasks.taxiordersservice.ui.details;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.testtasks.taxiordersservice.Application;
import com.testtasks.taxiordersservice.R;
import com.testtasks.taxiordersservice.data.order.Order;
import com.testtasks.taxiordersservice.ui.MainActivityCallback;
import com.testtasks.taxiordersservice.ui.base.BaseController;
import com.testtasks.taxiordersservice.ui.base.PresenterI;
import com.testtasks.taxiordersservice.utils.NetworkUtils;
import com.testtasks.taxiordersservice.utils.StringUtils;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailsController extends BaseController implements OrderDetailsControllerI {

    @BindView(R.id.car_image)
    ImageView carImage;
    @BindView(R.id.start_address)
    TextView startAddress;
    @BindView(R.id.end_address)
    TextView endAddress;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.car)
    TextView car;
    @BindView(R.id.driver)
    TextView driver;

    @Inject
    OrderDetailsPresenter presenter;

    private Order order;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_order_details, container, false);
        Application.getComponent().inject(this);
        ButterKnife.bind(this, view);

        initView();
        presenter.getVehicleImage(order.getVehicle().getPhoto(), NetworkUtils.isNetworkConnected(getActivity()));

        return view;
    }

    @Override
    public void initView() {
        presenter.onCreate(this);
        mainActivityCallback = (MainActivityCallback) getActivity();
        mainActivityCallback.setToolbar(R.string.order_details, true);
        mainActivityCallback.setMenu(true);

        startAddress.setText(StringUtils.getFullAddressString(order.getStartAddress()));
        endAddress.setText(StringUtils.getFullAddressString(order.getEndAddress()));
        date.setText(StringUtils.getDateAndTimeString(order.getOrderTime()));
        price.setText(StringUtils.getPriceString(order.getPrice()));
        car.setText(StringUtils.getCarModelAndNumberString(order.getVehicle()));
        driver.setText(order.getVehicle().getDriverName());
    }

    @Override
    public PresenterI getPresenter() {
        return presenter;
    }

    @Override
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public void setVehiclePhoto(Bitmap bitmap) {
        carImage.setImageBitmap(bitmap);
    }

    @Override
    public void showLoading(boolean isVisible) {
        mainActivityCallback.showLoading(isVisible);
    }
}
