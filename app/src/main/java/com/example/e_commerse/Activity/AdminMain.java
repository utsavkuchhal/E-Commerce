package com.example.e_commerse.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.e_commerse.Fragments.More_fragment;
import com.example.e_commerse.Fragments.OrdersFragment;
import com.example.e_commerse.Fragments.ProductFragment;
import com.example.e_commerse.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminMain extends AppCompatActivity {

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.amin_bottom_container)
    FrameLayout message_bottom_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        bottomNavigationView.setItemIconTintList(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.amin_bottom_container, new OrdersFragment()).commit();
    }


    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.orders:
                    fragment = new OrdersFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.amin_bottom_container, fragment).commit();
                    break;
                case R.id.products:
                    fragment = new ProductFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.amin_bottom_container, fragment).commit();
                    break;
                case R.id.more:
                    fragment = new More_fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.amin_bottom_container, fragment).commit();
                    break;
            }
            return true;
        }

    };
}