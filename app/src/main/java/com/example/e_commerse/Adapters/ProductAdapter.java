package com.example.e_commerse.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.e_commerse.Models.ProductModel;
import com.example.e_commerse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductAdapter extends ArrayAdapter<ProductModel> {

    Activity context;
    ArrayList<ProductModel> products;
    public ProductAdapter(@NonNull Activity context, ArrayList<ProductModel> products) {
        super(context, R.layout.product_pojo, products);
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_pojo,parent,false);
        TextView name = view.findViewById(R.id.tv_name);
        TextView price = view.findViewById(R.id.tv_price);
        Button add_to_basket = view.findViewById(R.id.btn_add_to_cart);



        price.setText(products.get(position).getPrice() + "");
        name.setText(products.get(position).getProductName());

        add_to_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                HashMap<String,Object> map = new HashMap<>();
                map.put("productId",products.get(position).getProductId());
                map.put("quantity",1);
                FirebaseDatabase.getInstance().getReference().child("basket").child(userId).child(products.get(position).getProductId()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }
}
