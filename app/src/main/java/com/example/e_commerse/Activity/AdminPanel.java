package com.example.e_commerse.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerse.Models.ProductModel;
import com.example.e_commerse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminPanel extends AppCompatActivity {


    @BindView(R.id.acTvCategory)
    AutoCompleteTextView acTvCategory;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.et_product_name)
    EditText etProductName;
    @BindView(R.id.et_description)
    EditText etDescription;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.iv_product_image)
    ImageView ivProductImage;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        ButterKnife.bind(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Categories();
    }

    @OnClick({R.id.btn_save})
    public void onclickAdd() {
        String name = etProductName.getText().toString().trim();
        int price = Integer.parseInt(etPrice.getText().toString());
        String img = "https://res.cloudinary.com/djbqlhith/image/upload/v1597523862/rqdrtwdrkwucdncdcltt.jpg";
        String desc = etProductName.getText().toString();
        String productId = mDatabase.push().getKey();
        String category = acTvCategory.getText().toString().trim();

        if (name.length() != 0 && img.length() != 0 && desc.length() != 0 && category.length() != 0) {


            mDatabase.child("Categories").child(category).child(productId).setValue(true);

            mDatabase.child("Products").child(productId).setValue(new ProductModel(name, price, img, productId, desc)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(AdminPanel.this, "Item added", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please  Enter All Fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void Categories() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getTextArray(R.array.categories));
        acTvCategory.setThreshold(1);//start searching from 1 character
        acTvCategory.setAdapter(adapter);   //set the adapter for displaying country name list
    }

}