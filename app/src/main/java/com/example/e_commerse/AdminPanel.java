package com.example.e_commerse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerse.Models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminPanel extends AppCompatActivity {

    EditText tv_name;
    EditText tv_price;
    Button add;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        add = findViewById(R.id.button4);
        mDatabase = FirebaseDatabase.getInstance().getReference();


    }

    public   void onclickAdd(View view){
        String name = tv_name.getText().toString().trim();
        int price = Integer.parseInt(tv_price.getText().toString());
        String img = "https://res.cloudinary.com/djbqlhith/image/upload/v1597523862/rqdrtwdrkwucdncdcltt.jpg";
        String desc = "arcu cursus euismod quis viverra nibh cras pulvinar mattis nunc";
        String productId = mDatabase.push().getKey();
        HashMap<String,Object> productModel = new HashMap<>();
        productModel.put("productName",name);
        productModel.put("price",price);
        productModel.put("imageUrl",img);
        productModel.put("productId",productId);
        productModel.put("description",desc);
        mDatabase.child("Products").child(productId).setValue(productModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AdminPanel.this, "Item added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}