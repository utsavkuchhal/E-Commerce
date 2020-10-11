package com.example.e_commerse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerse.Models.BasketModel;
import com.example.e_commerse.Models.OrderModel;
import com.example.e_commerse.Models.ProductModel;
import com.example.e_commerse.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderClass> {
    Context context;
    ArrayList<OrderModel> basketItems;
    ClickListener clickListener;


    public OrderAdapter(Context context, ArrayList<OrderModel> basketItems, ClickListener clickListener) {
        this.context = context;
        this.basketItems = basketItems;
        this.clickListener = clickListener;
    }

    @Override
    public OrderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orders_pojo, parent, false);
        return new OrderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderClass holder, int position) {
        holder.tvDate.setText(basketItems.get(position).getOrderTime() + "");
        holder.tvOrderid.setText("#" + basketItems.get(position).getOrderId().substring(0, 7));
        holder.tvTotal.setText(basketItems.get(position).getTotalAmount() + "");

        boolean isdelivered = basketItems.get(position).isDelivered();
        boolean isshipped = basketItems.get(position).isShipped();
        boolean iscancelled = basketItems.get(position).isCancelled();

        if (iscancelled) {
            holder.tvStatus.setText("Cancelled");
        } else if (isdelivered) {
            holder.tvStatus.setText("Completed");
        } else if (isshipped) {
            holder.tvStatus.setText("Shipped");
        }
    }


    @Override
    public int getItemCount() {
        return basketItems.size();
    }

    public interface ClickListener {
        void onItemClick(int position);
    }

    public class OrderClass extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_orderid)
        TextView tvOrderid;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_total)
        TextView tvTotal;
        @BindView(R.id.textView4)
        Button textView4;
        @BindView(R.id.cl_order)
        LinearLayout clOrder;

        public OrderClass(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
