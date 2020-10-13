package com.example.e_commerse.KotlinItemClass

class FetchItem(val description:String, val imageUrl:String, val price:Int, val productId:String, val productName:String) {
    constructor():this("","",9,"","")
}