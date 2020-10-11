package com.example.e_commerse.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerse.Activity.CategoryWiseActivity;
import com.example.e_commerse.Adapters.CategoryAdapter;
import com.example.e_commerse.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductFragment extends Fragment implements CategoryAdapter.ClickListener {

    @BindView(R.id.recycleView_category_admin)
    RecyclerView recycleViewCategoryAdmin;

    private ArrayList<String> listGroup;
    private CategoryAdapter categoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, view);
        listGroup = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(), listGroup,ProductFragment.this);
        recycleViewCategoryAdmin.setLayoutManager(new GridLayoutManager(getContext(),3));
        recycleViewCategoryAdmin.setAdapter(categoryAdapter);
        LoadCatgories();
        return view;
    }

    public void LoadCatgories() {
        FirebaseDatabase.getInstance().getReference().child("Categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    listGroup.add(dataSnapshot1.getKey());
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), CategoryWiseActivity.class);
        intent.putExtra("category", listGroup.get(position));
        startActivity(intent);
    }
}