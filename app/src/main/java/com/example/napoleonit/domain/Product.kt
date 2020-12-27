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

    fun calcDiscountPrice(): String {
        val result = price * (1 - (discount / 100.0))
        return if (result - result.toInt() == 0.0)
            "${result.toInt()}"
        else
            "${Math.round(result * 100) / 100}"
    }
}
