package com.example.napoleonit.domain

import android.os.Parcelable
import com.example.napoleonit.ui.Type
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
    val name: String,
    val surname: String,
    val phone: String,
    val selectedType: Type,
    val cart: List<Product>
) : Parcelable
