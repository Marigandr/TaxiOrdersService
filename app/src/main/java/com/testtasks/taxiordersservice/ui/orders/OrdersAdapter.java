package com.testtasks.taxiordersservice.ui.orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testtasks.taxiordersservice.R;
import com.testtasks.taxiordersservice.data.room.entity.Order;
import com.testtasks.taxiordersservice.ui.MainActivityCallback;
import com.testtasks.taxiordersservice.utils.StringUtils;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ActivityViewHolder> {
    private MainActivityCallback callback;
    private List<Order> orders;

    public OrdersAdapter(List<Order> orders, MainActivityCallback callback) {
        this.callback = callback;
        this.orders = orders;
    }

    @Override
    public OrdersAdapter.ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);

        return new OrdersAdapter.ActivityViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(OrdersAdapter.ActivityViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.startAddress.setText(StringUtils.getFullAddressString(order.getStartAddress().getCity(),
                order.getStartAddress().getAddress()));
        holder.endAddress.setText(StringUtils.getFullAddressString(order.getEndAddress().getCity(),
                order.getEndAddress().getAddress()));
        holder.date.setText(StringUtils.getDateString(order.getOrderTime()));
        holder.price.setText(StringUtils.getPriceString(order.getPrice()));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class ActivityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.start_address)
        TextView startAddress;
        @BindView(R.id.end_address)
        TextView endAddress;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.price)
        TextView price;

        ActivityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> callback.openOrderDetailsController(orders.get(getAdapterPosition())));
        }
    }
}