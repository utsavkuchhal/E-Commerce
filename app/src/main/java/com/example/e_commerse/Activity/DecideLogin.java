package com.example.e_commerse.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.e_commerse.LoginActivity;
import com.example.e_commerse.R;
import com.example.e_commerse.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DecideLogin extends AppCompatActivity {

    @BindView(R.id.main_join_now_btn)
    Button main_join_now_btn;

    @BindView(R.id.main_login_btn)
    Button main_login_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.main_login_btn)
    public void GoTOLogin(){
        startActivity(new Intent(DecideLogin.this, LoginActivity.class));
    }

    @OnClick(R.id.main_join_now_btn)
    public   void gotoregister(){
        startActivity(new Intent(DecideLogin.this, RegisterActivity.class));
    }
}