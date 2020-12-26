package com.example.napoleonit.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Product(
    val name: String,
    val price: Double,
    val discount: Int = 0,
    val img: String = "",
    val description: String = "") : Parcelable {

    fun calcDiscountPrice(): Double {
        return price * (1 - (discount / 100.0))
    }
}
