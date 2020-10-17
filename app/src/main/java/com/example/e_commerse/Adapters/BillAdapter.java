package com.example.e_commerse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerse.Models.BasketModel;
import com.example.e_commerse.Models.ProductModel;
import com.example.e_commerse.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillClass> {
    Context context;
    ArrayList<BasketModel> basketItems;
    ArrayList<ProductModel> items;
    BasketAdapter.ClickListener clickListener;


    public BillAdapter(Context context, ArrayList<BasketModel> basketItems, ArrayList<ProductModel> items, BasketAdapter.ClickListener clickListener) {
        this.context = context;
        this.basketItems = basketItems;
        this.items = items;
        this.clickListener = clickListener;
    }

    @Override
    public BillAdapter.BillClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.basket_pojo, parent, false);
        return new BillAdapter.BillClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.BillClass holder, int position) {
        holder.basketItem.setText(items.get(position).getProductName());
        holder.basketQuantity.setText(basketItems.get(position).getQuantity() + "");
    }


    @Override
    public int getItemCount() {
        return basketItems.size();
    }

    public interface ClickListener {
        void onItemClick(int position);
    }

    public class BillClass extends RecyclerView.ViewHolder {

        @BindView(R.id.basket_Image)
        ImageView basketImage;
        @BindView(R.id.basket_item)
        TextView basketItem;
        @BindView(R.id.basket_quantity)
        TextView basketQuantity;
        @BindView(R.id.btn_edit_cart)
        Button btnEditCart;
        @BindView(R.id.btn_delete)
        ImageButton btnDelete;

        public BillClass(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cl_commonClick)
        void onClick(View view) {
            switch (view.getId()) {
                case R.id.cl_commonClick:
                    clickListener.onItemClick(getAdapterPosition());
                    break;
            }
        }
    }
}
