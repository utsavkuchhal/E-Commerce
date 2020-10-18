package com.example.e_commerse.KotlinItemClass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class FetchItem(val description:String, val imageUrl:String, val price:Int, val productId:String, val productName:String): Parcelable {
    constructor():this("","",9,"","")
}