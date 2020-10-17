package com.example.e_commerse.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerse.Models.OrderModel;
import com.example.e_commerse.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailActivity extends AppCompatActivity {

    @BindView(R.id.rv_order)
    RecyclerView rvOrder;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_orderid)
    TextView tvOrderid;
    @BindView(R.id.tv_paymentmethod)
    TextView tvPaymentmethod;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_total_bill)
    TextView tvTotalBill;

    String orderId = "";
    String address = "";
    String paymentMethod = "";
    String TotalBill = "";
    String date = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        orderId = getIntent().getStringExtra("orderID");
    }

    public void getData(){
        FirebaseDatabase.getInstance().getReference().child("Orders").child(orderId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                OrderModel orderModel = dataSnapshot.getValue(OrderModel.class);
                date = orderModel.getOrderTime();
                TotalBill = orderModel.getTotalAmount() + "";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setData(){
        tvOrderid.setText(orderId);
        tvDate.setText(date);
        tvTotalBill.setText(TotalBill);
    }
}