package com.example.napoleonit.presentation

import com.example.napoleonit.data.CartDao
import com.example.napoleonit.data.entity.Product
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

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

interface DetailedView : MvpView {

    @AddToEndSingle
    fun setProduct(product: Product)

    @AddToEndSingle
    fun setIsInCart(inCart: Boolean)
}