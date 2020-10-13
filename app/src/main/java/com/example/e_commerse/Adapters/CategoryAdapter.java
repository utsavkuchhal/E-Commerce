package com.example.e_commerse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerse.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryClass> {

    Context context;
    ArrayList<String> orders;
    ClickListener clickListener;

    public CategoryAdapter(Context context, ArrayList<String> orders, ClickListener clickListener) {
        this.context = context;
        this.orders = orders;
        this.clickListener = clickListener;
    }

    @Override
    public CategoryClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_layout, parent, false);
        return new CategoryAdapter.CategoryClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryClass holder, int position) {
        holder.tv_category.setText(orders.get(position));
    }


    @Override
    public int getItemCount() {
        return orders.size();
    }

    public interface ClickListener {
        void onItemClick(int position);
    }

    public class CategoryClass extends RecyclerView.ViewHolder {
        @BindView(R.id.name_category)
        TextView tv_category;


        public CategoryClass(@NonNull View itemView) {
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
