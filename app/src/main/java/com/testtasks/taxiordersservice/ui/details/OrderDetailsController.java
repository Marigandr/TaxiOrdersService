package com.testtasks.taxiordersservice.ui.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.testtasks.taxiordersservice.R;
import com.testtasks.taxiordersservice.data.room.entity.Order;
import com.testtasks.taxiordersservice.ui.MainActivityCallback;
import com.testtasks.taxiordersservice.ui.base.BaseController;
import com.testtasks.taxiordersservice.ui.base.PresenterI;
import com.testtasks.taxiordersservice.utils.StringUtils;

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

    private Order order;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_order_details, container, false);
        ButterKnife.bind(this, view);

        initView();

        return view;
    }

    @Override
    public void initView() {
        mainActivityCallback = (MainActivityCallback) getActivity();
        mainActivityCallback.setToolbar(R.string.order_details, true);
        mainActivityCallback.setMenu(true);

        Picasso.get()
                .load(getActivity().getString(R.string.image_url) + order.getVehicle().getPhoto())
                .placeholder(R.drawable.ic_taxi)
                .into(carImage);
        startAddress.setText(StringUtils.getFullAddressString(order.getStartAddress().getCity(), order.getStartAddress().getAddress()));
        endAddress.setText(StringUtils.getFullAddressString(order.getEndAddress().getCity(), order.getEndAddress().getAddress()));
        date.setText(StringUtils.getDateAndTimeString(order.getOrderTime()));
        price.setText(StringUtils.getPriceString(order.getPrice()));
        car.setText(StringUtils.getCarModelAndNumberString(order.getVehicle()));
        driver.setText(order.getVehicle().getDriverName());
    }

    @Override
    public PresenterI getPresenter() {
        return null;
    }

    @Override
    public void setOrder(Order order) {
        this.order = order;
    }
}
