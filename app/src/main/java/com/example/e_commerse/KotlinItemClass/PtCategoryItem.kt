package com.example.e_commerse.KotlinItemClass

import com.example.e_commerse.R
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.pt_category_item.view.*

class PtCategoryItem(val itemCt:FetchItem): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.ctItem_product_name.text=itemCt.productName
        viewHolder.itemView.ctItem_product_des.text=itemCt.description
        Picasso.get().load(itemCt.imageUrl).into(viewHolder.itemView.ctItem_product_image)
    }

    override fun getLayout(): Int {
       return R.layout.pt_category_item
    }
}