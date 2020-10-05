package com.example.e_commerse.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.e_commerse.Adapters.AdminOrderAdapter;
import com.example.e_commerse.Basket;
import com.example.e_commerse.Models.BasketModel;
import com.example.e_commerse.Models.OrderModel;
import com.example.e_commerse.Models.ProductModel;
import com.example.e_commerse.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminOrders extends AppCompatActivity {

    ListView lv_admin_orders;
    ArrayList<OrderModel> orders;
    ArrayList<ProductModel> products;
    AdminOrderAdapter adminOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders);
        lv_admin_orders = findViewById(R.id.lv_adminorders);
        orders = new ArrayList<>();
        products = new ArrayList<>();
        adminOrderAdapter = new AdminOrderAdapter(this, orders);
        lv_admin_orders.setAdapter(adminOrderAdapter);
        ordersApiHit();
    }

    private void ordersApiHit() {
        FirebaseDatabase.getInstance().getReference().child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OrderModel orderModel = snapshot.getValue(OrderModel.class);
                    orders.add(orderModel);
                }
                adminOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}