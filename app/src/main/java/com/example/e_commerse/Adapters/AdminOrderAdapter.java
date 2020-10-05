package com.example.e_commerse.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.e_commerse.Models.BasketModel;
import com.example.e_commerse.Models.OrderModel;
import com.example.e_commerse.Models.ProductModel;
import com.example.e_commerse.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdminOrderAdapter extends ArrayAdapter<OrderModel> {
    Activity context;
    ArrayList<OrderModel> orders;
    public AdminOrderAdapter(@NonNull Activity context, ArrayList<OrderModel> orders) {
        super(context, R.layout.admin_order_pojo, orders);
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_order_pojo,parent,false);
        TextView orderId = view.findViewById(R.id.textView7);
        TextView orderItems = view.findViewById(R.id.textView8);
        TextView quantity = view.findViewById(R.id.textView6);
        TextView address = view.findViewById(R.id.textView10);
        TextView userId = view.findViewById(R.id.textView11);
        Button btn_cancel = view.findViewById(R.id.button13);
        Button btn_shipped = view.findViewById(R.id.button14);
        Button btn_deliverd = view.findViewById(R.id.button15);
        TextView tv_date = view.findViewById(R.id.textView12);
        ConstraintLayout cl_order = view.findViewById(R.id.cl_order);

        if (orders != null){
            orderId.setText(orders.get(position).getOrderId());
            userId.setText(orders.get(position).getBuyerId());
            tv_date.setText(orders.get(position).getOrderTime());

            boolean cancel = orders.get(position).isCancelled();
            boolean delivered = orders.get(position).isDelivered();
            boolean shipped = orders.get(position).isShipped();



            if (cancel){
                cl_order.setBackgroundResource(R.color.colocancelled);
            }else if (shipped && !delivered){
                cl_order.setBackgroundResource(R.color.colorshipped);
            }else if(delivered){
                cl_order.setBackgroundResource(R.color.colorDelivered);
            }
        }

        btn_deliverd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().getDatabase().getReference().child("Orders").child(orders.get(position).getOrderId()).child("delivered").setValue(true);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().getDatabase().getReference().child("Orders").child(orders.get(position).getOrderId()).child("cancelled").setValue(true);
            }
        });

        btn_shipped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().getDatabase().getReference().child("Orders").child(orders.get(position).getOrderId()).child("shipped").setValue(true);
            }
        });
        return view;
    }
}
