package com.example.e_commerse.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerse.Models.OrderModel;
import com.example.e_commerse.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderClass> {

    Context context;
    ArrayList<OrderModel> orders;

    public OrdersAdapter(Context context, ArrayList<OrderModel> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_order_pojo, parent, false);
        return new OrderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderClass holder, int position) {
        if (orders != null && orders.size() > 1){
            holder.tvOrderId.setText(orders.get(position).getOrderId());
            holder.tvTotal.setText(orders.get(position).getTotalAmount() + "");
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrderClass extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_detailImage)
        ImageView ivDetailImage;
        @BindView(R.id.tv_product_name)
        TextView tvProductName;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_quantity)
        TextView tvQuantity;
        @BindView(R.id.tv_total)
        TextView tvTotal;
        @BindView(R.id.tv_orderId)
        TextView tvOrderId;

        public OrderClass(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
