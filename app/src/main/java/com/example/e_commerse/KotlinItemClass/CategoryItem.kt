package com.example.e_commerse.KotlinItemClass

import com.example.e_commerse.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CategoryItem :Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    ///Implement later
    }

    override fun getLayout(): Int {
        return R.layout.category_layout
    }
}