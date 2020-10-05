package com.example.e_commerse.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.e_commerse.Models.BasketModel;
import com.example.e_commerse.Models.OrderModel;
import com.example.e_commerse.Models.ProductModel;
import com.example.e_commerse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderAdapter extends ArrayAdapter<OrderModel> {
    Activity context;
    ArrayList<OrderModel> order;

    public OrderAdapter(@NonNull Activity context,ArrayList<OrderModel> orders) {
        super(context, R.layout.product_pojo, orders);
        this.context = context;
        this.order = orders;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.orders_pojo,parent,false);
        TextView OrderedOn = view.findViewById(R.id.textView2);
        TextView totalPrice = view.findViewById(R.id.textView3);
        TextView items = view.findViewById(R.id.textView4);
        ConstraintLayout cl_order = view.findViewById(R.id.cl_order);

        if (order != null) {
            OrderedOn.setText(order.get(position).getOrderTime() + "");
            totalPrice.setText(order.get(position).getTotalAmount() + "");
            boolean cancel = order.get(position).isCancelled();
            boolean delivered = order.get(position).isDelivered();
            boolean shipped = order.get(position).isShipped();



            if (cancel){
                cl_order.setBackgroundResource(R.color.colocancelled);
            }else if (shipped && !delivered){
                cl_order.setBackgroundResource(R.color.colorshipped);
            }else if(delivered){
                cl_order.setBackgroundResource(R.color.colorDelivered);
            }
        }


        return view;
    }
}
