package com.example.e_commerse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerse.Adapters.BasketAdapter;
import com.example.e_commerse.Adapters.ProductAdapter;
import com.example.e_commerse.Models.BasketModel;
import com.example.e_commerse.Models.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Basket extends AppCompatActivity {

    TextView tv_total;
    Button btn_place_order;
    ListView lv_basket;
    ArrayList<BasketModel> basketItems;
    ArrayList<ProductModel> items;
    BasketAdapter basketAdapter;
    int Total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        tv_total = findViewById(R.id.textView5);
        btn_place_order = findViewById(R.id.button7);
        lv_basket = findViewById(R.id.lv_basket);
        basketItems = new ArrayList<>();
        items = new ArrayList<>();
        basketAdapter = new BasketAdapter(this, items, basketItems);
        lv_basket.setAdapter(basketAdapter);

        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderId = FirebaseDatabase.getInstance().getReference().push().getKey();
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                Map<String,Object> map = new HashMap<>();
                map.put("orderId",orderId);
                map.put("basket",basketItems);
                map.put("buyerId",FirebaseAuth.getInstance().getCurrentUser().getUid());
                map.put("delivered",false);
                map.put("shipped",false);
                map.put("cancelled",false);
                map.put("totalAmount", Total);
                map.put("orderTime", date);
                FirebaseDatabase.getInstance().getReference().child("Orders").child(orderId).setValue(map);
                FirebaseDatabase.getInstance().getReference().child("basket").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
            }
        });
        basketApiHit();
    }

    private void basketApiHit() {
        FirebaseDatabase.getInstance().getReference().child("basket").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BasketModel basktItem = snapshot.getValue(BasketModel.class);
                    basketItems.add(basktItem);
                }

                FirebaseDatabase.getInstance().getReference().child("Products").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            ProductModel productModel = dataSnapshot1.getValue(ProductModel.class);

                            for (BasketModel temp : basketItems){
                                if (temp.getProductId().equalsIgnoreCase(productModel.getProductId())){
                                    items.add(productModel);
                                    Total += productModel.getPrice()  * temp.getQuantity();
                                }
                            }
                        }
                        tv_total.setText(Total + "");
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
}