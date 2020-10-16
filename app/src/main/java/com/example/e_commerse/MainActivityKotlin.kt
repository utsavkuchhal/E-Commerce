package com.example.e_commerse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.e_commerse.KotlinItemClass.CategoryItem
import com.example.e_commerse.KotlinItemClass.MainItem
import com.example.e_commerse.ktActivitys.CategoryItemActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main_kotlin.*


class MainActivityKotlin : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)

        setSupportActionBar(findViewById(R.id.toolbar))

        val drawerToggle = ActionBarDrawerToggle(this, drawer,R.string.open, R.string.close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        navigation_view.setNavigationItemSelectedListener(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter2=GroupAdapter<GroupieViewHolder>()
            adapter2.add(MainItem())
            adapter2.add(MainItem())
            adapter2.add(MainItem())
            adapter2.add(MainItem())
        recycleView_main_item.adapter=adapter2

        val adapter3=GroupAdapter<GroupieViewHolder>()
        adapter3.add(MainItem())
        adapter3.add(MainItem())
        adapter3.add(MainItem())
        adapter3.add(MainItem())
        recycleView_main_item_second.adapter=adapter3
        fetchCategoryItem()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_actionbar_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }



    private fun fetchCategoryItem(){
       val ref= FirebaseDatabase.getInstance().getReference("/Categories")
        ref.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val adapter=GroupAdapter<GroupieViewHolder>()

                p0.children.forEach{
                    Log.d("Get items", it.toString())
                    val catItems=it.key.toString()
                    adapter.add(CategoryItem(catItems))
                }

                adapter.setOnItemClickListener { item, view ->
                    val itemName= item as CategoryItem
                    val intent=Intent(view.context,CategoryItemActivity::class.java)
                        intent.putExtra("ctName",itemName.ctItem)
                        startActivity(intent)
                        }

                recycleView_category.adapter=adapter
            }
            override fun onCancelled(p0: DatabaseError) {

            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
            }
            R.id.Actionbar_cart->{
                val intent=Intent(this,Basket::class.java)
                    startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_home -> {
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()

                val intent=Intent(this,MainActivityKotlin::class.java)
                startActivity(intent)
            }
            R.id.nav_orders -> {
                Toast.makeText(this, "Orders", Toast.LENGTH_SHORT).show()

                val intent=Intent(this,Orders::class.java)
                startActivity(intent)
            }
            R.id.nav_products -> {
                Toast.makeText(this, "Products", Toast.LENGTH_SHORT).show()

                val intent=Intent(this,Products::class.java)
                startActivity(intent)
            }
            R.id.nav_basket -> {
                Toast.makeText(this, "Basket", Toast.LENGTH_SHORT).show()

                val intent=Intent(this,Basket::class.java)
                startActivity(intent)
            }
            R.id.nav_customerCare -> {
                Toast.makeText(this, "Customer care", Toast.LENGTH_SHORT).show()

            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()


            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}