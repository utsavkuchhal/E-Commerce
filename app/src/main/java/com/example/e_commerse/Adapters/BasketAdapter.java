package com.example.e_commerse.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.e_commerse.Basket;
import com.example.e_commerse.Models.BasketModel;
import com.example.e_commerse.Models.ProductModel;
import com.example.e_commerse.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BasketAdapter extends ArrayAdapter<ProductModel> {
    Activity context;
    ArrayList<ProductModel> products;
    ArrayList<BasketModel> items;

    public BasketAdapter(@NonNull Activity context, ArrayList<ProductModel> products,ArrayList<BasketModel> basketModels) {
        super(context, R.layout.product_pojo, products);
        this.context = context;
        this.products = products;
        this.items = basketModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.basket_pojo,parent,false);
        TextView name = view.findViewById(R.id.textView2);
        TextView desc = view.findViewById(R.id.textView3);
        TextView price = view.findViewById(R.id.textView4);
        TextView quantity = view.findViewById(R.id.textView6);
        Button btnRemove = view.findViewById(R.id.button8);

        if (products != null && items != null) {
            price.setText(products.get(position).getPrice() + "");
            desc.setText(products.get(position).getDescription());
            name.setText(products.get(position).getProductName());
            quantity.setText(items.get(position).getQuantity() + "");

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase.getInstance().getReference().child("basket").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(products.get(position).getProductId()).removeValue();
                    Toast.makeText(context, "Items Removed", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                    //TODO change Improve List view UI on delete of any items
                }
            });

        }
        return view;
    }
}
