package com.example.napoleonit.presentation

import com.example.napoleonit.data.CartDao
import com.example.napoleonit.domain.Product
import com.example.napoleonit.ui.CartView
import moxy.MvpPresenter
import javax.inject.Inject

class CartPresenter @Inject constructor(private val cartDao: CartDao)
    : MvpPresenter<CartView>() {

    private var cart: List<Product> = emptyList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        cart = cartDao.getAllFromCart()
        viewState.setCart(cart)
    }

    fun onDeleteClick(product: Product) {
        cart = cart.filter { it != product }
        cartDao.deleteFromCart(product)
        viewState.setCart(cart)
    }
}
