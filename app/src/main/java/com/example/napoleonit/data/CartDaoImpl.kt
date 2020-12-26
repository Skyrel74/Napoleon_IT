package com.example.napoleonit.data

import android.content.SharedPreferences
import com.example.napoleonit.data.entity.Product
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CartDaoImpl(
    private val sharedPreferences: SharedPreferences
) : CartDao {

    private var cart: List<Product>
        get() = sharedPreferences.getString(CART_TAG, null)?.let {
            Json.decodeFromString<List<Product>>(it)
        } ?: emptyList()
        set(product) {
            sharedPreferences.edit()
                .putString(CART_TAG, Json.encodeToString(product))
                .apply()
        }

    override fun addToCart(product: Product) {
        cart = cart + product
    }

    override fun deleteFromCart(product: Product) {
        cart = cart.filter { it != product }
    }

    override fun getAllFromCart(): List<Product> = cart

    override fun clearCart() {
        cart = emptyList()
    }

    override fun isInCart(product: Product): Boolean {
        return cart.contains(product)
    }

    companion object {
        private const val CART_TAG = "CART_TAG"
    }
}