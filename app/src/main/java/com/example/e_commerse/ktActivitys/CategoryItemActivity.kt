package com.example.e_commerse.ktActivitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.e_commerse.KotlinItemClass.PtCategoryItem
import com.example.e_commerse.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_category_item.*
import java.util.*

class CategoryItemActivity : AppCompatActivity() {

    private var categoryName:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_item)

        categoryName =intent.getStringExtra("ctName")


        fetchItem()
    }

    private fun fetchItem(){
        val ref=FirebaseDatabase.getInstance().getReference("/Categories/$categoryName")

        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val adapter=GroupAdapter<GroupieViewHolder>()

                p0.children.forEach {
                    Log.d("getProductId",it.key.toString())

                }



                FirebaseDatabase.getInstance().getReference("Products")
                        .addListenerForSingleValueEvent(object :ValueEventListener{

                            override fun onDataChange(p0: DataSnapshot) {
                                p0.children.forEach {

                                    adapter.add(PtCategoryItem())
                                }
                            }

                            override fun onCancelled(p0: DatabaseError) {

                            }

                        })

                recycleView_pt_category_item.adapter=adapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })
    }
}