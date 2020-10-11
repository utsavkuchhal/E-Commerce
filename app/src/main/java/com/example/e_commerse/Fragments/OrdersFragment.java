package com.example.e_commerse.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.e_commerse.Adapters.OrderAdapter;
import com.example.e_commerse.Adapters.OrdersAdapter;
import com.example.e_commerse.Models.OrderModel;
import com.example.e_commerse.Models.ProductModel;
import com.example.e_commerse.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrdersFragment extends Fragment {


    @BindView(R.id.rv_orders)
    RecyclerView rv_orders;
    @BindView(R.id.tv_pending)
    TextView tv_pending;
    @BindView(R.id.tv_shipped)
    TextView tv_shipped;

    OrdersAdapter orderAdapter;
    ArrayList<ProductModel> products;
    ArrayList<OrderModel> orders;

    boolean ordersPending = false;
    boolean orderShipped = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        ButterKnife.bind(this, view);
        products = new ArrayList<>();
        orders = new ArrayList<>();
        orderAdapter = new OrdersAdapter(getContext(),orders);
        rv_orders.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_orders.setAdapter(orderAdapter);
        loadOrdersPending();
        return view;
    }

    @OnClick({R.id.tv_pending,R.id.tv_shipped})
    public void OnTvClicked(View view){
        switch (view.getId()){
            case R.id.tv_pending:
                ordersPending = true;
                orderShipped = false;
                tv_pending.setTextColor(Color.WHITE);
                tv_pending.setBackgroundResource(R.color.colorOrange);
                tv_shipped.setTextColor(Color.BLACK);
                tv_shipped.setBackgroundResource(R.color.white);
                tv_pending.setClickable(false);
                tv_shipped.setClickable(true);
                loadOrdersPending();
                break;
            case R.id.tv_shipped:
                orderShipped = true;
                ordersPending = false;
                tv_shipped.setTextColor(Color.WHITE);
                tv_shipped.setBackgroundResource(R.color.colorOrange);
                tv_pending.setTextColor(Color.BLACK);
                tv_pending.setBackgroundResource(R.color.white);
                tv_shipped.setClickable(false);
                tv_pending.setClickable(true);
                loadOrderShipped();        }

    }

    private void loadOrderShipped() {
        orders.clear();
        FirebaseDatabase.getInstance().getReference().child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OrderModel orderModel = snapshot.getValue(OrderModel.class);
                    if (orderModel.isShipped() && !orderModel.isCancelled() && !orderModel.isDelivered())
                        orders.add(snapshot.getValue(OrderModel.class));
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadOrdersPending() {
        orders.clear();
        FirebaseDatabase.getInstance().getReference().child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OrderModel orderModel = snapshot.getValue(OrderModel.class);
                    if (!orderModel.isCancelled() && !orderModel.isDelivered() && !orderModel.isShipped())
                    orders.add(snapshot.getValue(OrderModel.class));
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}