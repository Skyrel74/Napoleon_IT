package com.example.napoleonit.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductX(
    @SerialName("discount")
    val discount: Int?,
    @SerialName("img")
    val img: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("price")
    val price: Double?,
    @SerialName("description")
    val description: String?
)
