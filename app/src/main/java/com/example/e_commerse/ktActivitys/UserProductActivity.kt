package com.example.e_commerse.ktActivitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.widget.Toast
import com.example.e_commerse.KotlinItemClass.CartInfo
import com.example.e_commerse.KotlinItemClass.FetchItem
import com.example.e_commerse.KotlinItemClass.UserReview
import com.example.e_commerse.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_user_product.*
import java.util.*

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

        user_product_addCart.setOnClickListener {
            addToCart()
        }
    }

    private fun addToCart(){
        val productDet=intent.getParcelableExtra<FetchItem>("ProductD")
        val userUid=FirebaseAuth.getInstance().uid.toString()
        val cartInfo=CartInfo("${productDet!!.productId}",1)

        FirebaseDatabase.getInstance().getReference("basket").child(userUid).child(productDet!!.productId).setValue(cartInfo)
                .addOnCompleteListener {
                    Toast.makeText(this,"Added to cart",Toast.LENGTH_SHORT).show()
                }
    }
}