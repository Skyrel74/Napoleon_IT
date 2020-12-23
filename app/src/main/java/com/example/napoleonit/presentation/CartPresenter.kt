package com.example.napoleonit.presentation

import com.example.napoleonit.data.CartDao
import com.example.napoleonit.domain.Product
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

class CartPresenter(private val cartDao: CartDao) : MvpPresenter<CartView>() {

    private var cart: List<Product> = emptyList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val cart = cartDao.getAllFromCart()
        viewState.setCart(cart)
    }

//    fun onDeleteClick(product: Product) {
//        cart = cart.filter { it != product }
//        viewState.setCart(cart)
//    }
}

interface CartView : MvpView {

    @AddToEndSingle
    fun setCart(cart: List<Product>)
}