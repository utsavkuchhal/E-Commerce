package com.example.e_commerse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.e_commerse.Adapters.OrderAdapter;
import com.example.e_commerse.Models.OrderModel;
import com.example.e_commerse.Models.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Orders extends AppCompatActivity {

    ListView lv_orders;
    ArrayList<ProductModel> products;
    ArrayList<OrderModel> orders;
    OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        lv_orders = findViewById(R.id.lv_orders);
        products = new ArrayList<>();
        orders = new ArrayList<>();
        orderAdapter = new OrderAdapter(this,orders);
        lv_orders.setAdapter(orderAdapter);
        OrderApiHit();
    }

    public void OrderApiHit() {
        FirebaseDatabase.getInstance().getReference().child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
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