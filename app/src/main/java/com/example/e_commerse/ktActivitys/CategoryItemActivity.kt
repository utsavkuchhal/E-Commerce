package com.example.e_commerse.ktActivitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.e_commerse.KotlinItemClass.FetchItem
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
        recycleView_pt_category_item.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        categoryName =intent.getStringExtra("ctName")

        fetchItem()
    }

    private fun fetchItem(){
        var itemKeyList= mutableListOf<String>()
        val ref=FirebaseDatabase.getInstance().getReference("/Categories/$categoryName")

        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val adapter=GroupAdapter<GroupieViewHolder>()


                p0.children.forEach {
                    //Log.d("getProductId",it.key.toString())
                        itemKeyList.add(it.key.toString())
                }



                FirebaseDatabase.getInstance().getReference("Products")
                        .addListenerForSingleValueEvent(object :ValueEventListener{

                            override fun onDataChange(p0: DataSnapshot) {
                                p0.children.forEach {
                                    if(itemKeyList.contains(it.key.toString())) {
                                        val itemsCt = it.getValue(FetchItem()::class.java)
                                        if (itemsCt != null) {
                                            adapter.add(PtCategoryItem(itemsCt))
                                        }
                                    }
                                }

                                adapter.setOnItemClickListener { item, view ->
                                    val ptProduct=item as PtCategoryItem
                                    val intent=Intent(view.context,UserProductActivity::class.java)
                                        intent.putExtra("ProductD",item.itemCt)
                                    startActivity(intent)

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