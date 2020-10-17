package com.example.e_commerse;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerse.Activity.OrderDetailActivity;
import com.example.e_commerse.Adapters.OrderAdapter;
import com.example.e_commerse.Models.OrderModel;
import com.example.e_commerse.Models.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Orders extends AppCompatActivity implements OrderAdapter.ClickListener {

    ArrayList<OrderModel> orders;
    OrderAdapter orderAdapter;

    @BindView(R.id.lv_orders)
    RecyclerView lvOrders;

    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        ButterKnife.bind(this);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        orders = new ArrayList<>();
        orderAdapter = new OrderAdapter(this, orders, Orders.this);
        lvOrders.setLayoutManager(new LinearLayoutManager(this));
        lvOrders.setHasFixedSize(true);
        lvOrders.setAdapter(orderAdapter);
        OrderApiHit();
    }

    public void OrderApiHit() {
        FirebaseDatabase.getInstance().getReference().child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OrderModel orderModel = snapshot.getValue(OrderModel.class);
                    if (orderModel.getBuyerId().equalsIgnoreCase(firebaseUser.getUid()))
                        orders.add(snapshot.getValue(OrderModel.class));
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(Orders.this, OrderDetailActivity.class);
        intent.putExtra("orderID",orders.get(position).getOrderId());
        startActivity(intent);
    }
}