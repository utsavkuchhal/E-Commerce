package com.example.e_commerse.ktActivitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_commerse.KotlinItemClass.FetchItem
import com.example.e_commerse.KotlinItemClass.UserReview
import com.example.e_commerse.R
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_user_product.*

class UserProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_product)

        val productDetails = intent.getParcelableExtra<FetchItem>("ProductD")
        if (productDetails != null) {
            Picasso.get().load(productDetails.imageUrl).into(user_product_image)
            user_product_name.text=productDetails.productName
            user_product_price.text=productDetails.price.toString()
            user_product_des.text=productDetails.description
        }


        val adapter=GroupAdapter<GroupieViewHolder>()
            adapter.add(UserReview())
            adapter.add(UserReview())
            adapter.add(UserReview())
            adapter.add(UserReview())
        user_product_review_recycleView.adapter=adapter
    }
}