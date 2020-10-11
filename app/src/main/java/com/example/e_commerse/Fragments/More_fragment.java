package com.example.e_commerse.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.e_commerse.Activity.AdminOrderHistory;
import com.example.e_commerse.Activity.AdminPanel;
import com.example.e_commerse.Activity.DecideLogin;
import com.example.e_commerse.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class More_fragment extends Fragment {


    @BindView(R.id.tv_faqs)
    TextView tvFaqs;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.tv_deals)
    TextView tvDeals;
    @BindView(R.id.tv_add)
    TextView tvCoupons;
    @BindView(R.id.tv_orderHistory)
    TextView tvOrderHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.tv_faqs,R.id.tv_logout,R.id.tv_deals,R.id.tv_add,R.id.tv_orderHistory})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), DecideLogin.class));
                break;
            case R.id.tv_add:
                startActivity(new Intent(getContext(), AdminPanel.class));
                break;
            case R.id.tv_orderHistory:
                startActivity(new Intent(getContext(), AdminOrderHistory.class));
                break;
        }
    }
}