package com.example.e_commerse.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerse.Adapters.OrderAdapter;
import com.example.e_commerse.Adapters.OrdersAdapter;
import com.example.e_commerse.Models.OrderModel;
import com.example.e_commerse.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminOrderHistory extends AppCompatActivity {

    @BindView(R.id.rv_orderHistory)
    RecyclerView tvOrderHistory;

    FirebaseUser firebaseUser;
    ArrayList<OrderModel> orderhistory;
    OrdersAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_history);
        ButterKnife.bind(this);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        orderhistory = new ArrayList<>();
        orderAdapter = new OrdersAdapter(this, orderhistory);
        tvOrderHistory.setLayoutManager(new LinearLayoutManager(this));
        tvOrderHistory.setAdapter(orderAdapter);
        getOrderHistory();
    }

    public void getOrderHistory() {
        FirebaseDatabase.getInstance().getReference().child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    OrderModel orderModel = dataSnapshot1.getValue(OrderModel.class);
                    if (orderModel.isDelivered()) {
                        orderhistory.add(orderModel);
                    }
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}