package com.example.e_commerse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_commerse.KotlinItemClass.CategoryItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main_kotlin.*

class MainActivityKotlin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)

        val adapter=GroupAdapter<GroupieViewHolder>()

        adapter.add(CategoryItem())
        adapter.add(CategoryItem())
        adapter.add(CategoryItem())
        recycleView_category.adapter=adapter
    }
}