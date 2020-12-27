package com.example.napoleonit.presentation

import com.example.napoleonit.data.CartDao
import com.example.napoleonit.domain.Product
import com.example.napoleonit.ui.DetailedView
import moxy.MvpPresenter
import javax.inject.Inject

class DetailedPresenter(
    private val product: Product,
    private val cartDao: CartDao
) : MvpPresenter<DetailedView>() {

    private var isInCart: Boolean = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setProduct(product)
        isInCart = cartDao.isInCart(product)
        viewState.setIsInCart(isInCart)
    }

    fun onCartClicked() {
        if (isInCart)
            cartDao.deleteFromCart(product)
        else
            cartDao.addToCart(product)
        isInCart = !isInCart
        viewState.setIsInCart(isInCart)
    }
}

class DetailedPresenterFactory @Inject constructor(
    private val cartDao: CartDao
) {
    fun create(product: Product) = DetailedPresenter(product, cartDao)
}
