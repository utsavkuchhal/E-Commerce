package com.example.e_commerse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_commerse.Activity.AdminMain;
import com.example.e_commerse.Activity.AdminPanel;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button logout,add,items,cart,myorders,adminOrders,kotlin_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = findViewById(R.id.button3);
        add = findViewById(R.id.button5);
        items = findViewById(R.id.button6);
        cart = findViewById(R.id.button9);
        myorders = findViewById(R.id.button11);
        adminOrders = findViewById(R.id.button10);
        kotlin_main=findViewById(R.id.kt_main);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdminPanel.class));
            }
        });

        items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Products.class));
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Basket.class));
            }
        });

        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Orders.class));
            }
        });

        adminOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdminMain.class));
            }
        });

        kotlin_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivityKotlin.class));
            }
        });
    }
}