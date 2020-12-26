package com.example.napoleonit.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    @SerialName("products")
    val products: List<ProductX>?
)