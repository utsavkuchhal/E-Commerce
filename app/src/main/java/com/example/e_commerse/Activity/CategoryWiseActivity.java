package com.example.e_commerse.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerse.Models.ProductModel;
import com.example.e_commerse.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryWiseActivity extends AppCompatActivity {

    @BindView(R.id.rv_listItem_category)
    RecyclerView rvListItemCategory;

    ArrayList<ProductModel> CategoryProducts;
    AdminProductViewAdapter adminProductViewAdapter;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_wise);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        ButterKnife.bind(this);
        CategoryProducts = new ArrayList<>();
        adminProductViewAdapter = new AdminProductViewAdapter(this, CategoryProducts);
        rvListItemCategory.setLayoutManager(new LinearLayoutManager(this));
        rvListItemCategory.setHasFixedSize(true);
        rvListItemCategory.setAdapter(adminProductViewAdapter);
        getCategoryWiseProducts();
    }

    public void getCategoryWiseProducts() {
        ArrayList<String> prducIds = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Categories").child(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    prducIds.add(snapshot.getKey());
                }
                FirebaseDatabase.getInstance().getReference().child("Products").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            ProductModel productModel = dataSnapshot1.getValue(ProductModel.class);
                            for (String id : prducIds) {
                                if (productModel.getProductId().equalsIgnoreCase(id)) {
                                    CategoryProducts.add(productModel);
                                }
                            }
                        }
                        adminProductViewAdapter.notifyDataSetChanged();
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