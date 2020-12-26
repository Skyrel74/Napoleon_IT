package com.example.napoleonit.data.entity

import android.os.Parcelable
import com.example.napoleonit.ui.Type
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(val name: String, val surname: String, val phone: String, val selectedType: Type) :
    Parcelable