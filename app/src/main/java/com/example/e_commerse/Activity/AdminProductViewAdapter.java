package com.example.e_commerse.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerse.Adapters.OrdersAdapter;
import com.example.e_commerse.Models.ProductModel;
import com.example.e_commerse.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminProductViewAdapter extends RecyclerView.Adapter<AdminProductViewAdapter.AdminView> {

    Context context;
    ArrayList<ProductModel> productModels;

    public AdminProductViewAdapter(Context context, ArrayList<ProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public AdminView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_product_pojo, parent, false);
        return new AdminProductViewAdapter.AdminView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminView holder, int position) {
        ProductModel productModel = productModels.get(position);
        if (productModels == null) {
            if (productModels.size() > 0) {
                holder.tv_description.setText(productModel.getDescription());
                holder.tv_price.setText(productModel.getPrice() + "");
                holder.tv_product_name.setText(productModel.getProductName());
            }
        }
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class AdminView extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_product_name)
        TextView tv_product_name;
        @BindView(R.id.tv_description)
        TextView tv_description;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.iv_detailImage)
        ImageView iv_detailImage;
        @BindView(R.id.btn_stock)
        TextView btn_stock;

        public AdminView(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
