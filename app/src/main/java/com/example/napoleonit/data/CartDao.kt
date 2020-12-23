package com.example.napoleonit.data

import com.example.napoleonit.domain.Product

interface CartDao {

    fun addToCart(product: Product)
    fun deleteFromCart(product: Product)
    fun getAllFromCart(): List<Product>
    fun clearCart()
    fun isInCart(product: Product): Boolean
}