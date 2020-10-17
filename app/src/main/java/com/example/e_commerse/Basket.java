package com.example.e_commerse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerse.Adapters.BasketAdapter;
import com.example.e_commerse.Adapters.CategoryAdapter;
import com.example.e_commerse.Models.BasketModel;
import com.example.e_commerse.Models.OrderModel;
import com.example.e_commerse.Models.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Basket extends AppCompatActivity implements BasketAdapter.ClickListener {

    ArrayList<BasketModel> basketItems;
    ArrayList<ProductModel> items;
    BasketAdapter basketAdapter;
    int Total = 0;


    @BindView(R.id.lv_basket)
    RecyclerView lvBasket;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.btn_place_order)
    Button btnPlaceOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        ButterKnife.bind(this);
        basketItems = new ArrayList<>();
        items = new ArrayList<>();
        basketAdapter = new BasketAdapter(this, basketItems, items, Basket.this);
        lvBasket.setLayoutManager(new LinearLayoutManager(this));
        lvBasket.setAdapter(basketAdapter);
        basketApiHit();
    }

    private void basketApiHit() {
        FirebaseDatabase.getInstance().getReference().child("basket").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String productid = (String) snapshot.getKey();
                    int quantity = Integer.parseInt(snapshot.getValue().toString());
                    basketItems.add(new BasketModel(productid, quantity));
                }

                FirebaseDatabase.getInstance().getReference().child("Products").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            ProductModel productModel = dataSnapshot1.getValue(ProductModel.class);

                            for (BasketModel temp : basketItems) {
                                if (temp.getProductId().equalsIgnoreCase(productModel.getProductId())) {
                                    items.add(productModel);
                                    Total += productModel.getPrice() * temp.getQuantity();
                                }
                            }
                        }
                        tvTotal.setText("₹ " + Total);
                        basketAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.btn_place_order)
    public void orderOnClick() {
        String orderId = FirebaseDatabase.getInstance().getReference().push().getKey();
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().child("Orders").child(orderId).setValue(new OrderModel());

    }

    @Override
    public void onItemClick(int position) {
        String orderId = FirebaseDatabase.getInstance().getReference().push().getKey();
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("basket", basketItems);
        map.put("buyerId", FirebaseAuth.getInstance().getCurrentUser().getUid());
        map.put("delivered", false);
        map.put("shipped", false);
        map.put("cancelled", false);
        map.put("totalAmount", Total);
        map.put("orderTime", date);
        FirebaseDatabase.getInstance().getReference().child("Orders").child(orderId).setValue(map);
        FirebaseDatabase.getInstance().getReference().child("basket").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
    }
}