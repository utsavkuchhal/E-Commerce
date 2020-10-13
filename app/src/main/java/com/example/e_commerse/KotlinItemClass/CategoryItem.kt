package com.example.e_commerse.KotlinItemClass

import com.example.e_commerse.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.category_layout.view.*

class CategoryItem(val ctItem: String) :Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.name_category.text=ctItem
    }

    override fun getLayout(): Int {
        return R.layout.category_layout
    }
}