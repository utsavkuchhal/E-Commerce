package com.example.e_commerse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.e_commerse.Adapters.ProductAdapter;
import com.example.e_commerse.Models.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Products extends AppCompatActivity {

    ArrayList<ProductModel> products ;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        listView = findViewById(R.id.lv_prodcuts);
        products = new ArrayList<>();
        ProductApiHit();
    }

    private void ProductApiHit(){
        FirebaseDatabase.getInstance().getReference().child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ProductModel productModel = snapshot.getValue(ProductModel.class);
                    products.add(productModel);
                }
                listView.setAdapter(new ProductAdapter(Products.this,products));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}