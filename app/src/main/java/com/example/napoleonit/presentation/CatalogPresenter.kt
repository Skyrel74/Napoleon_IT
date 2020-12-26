package com.example.napoleonit.presentation

import com.example.napoleonit.domain.GetAllProductsUseCase
import com.example.napoleonit.domain.Product
import com.example.napoleonit.extentions.launchWithErrorHandler
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenterScope
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import javax.inject.Inject

class CatalogPresenter @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : MvpPresenter<CatalogView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading(true)
        presenterScope.launchWithErrorHandler(
            block = {
                val products = getAllProductsUseCase()
                viewState.setProducts(products)
                viewState.showLoading(false)
            }, onError = {
                viewState.showLoading(false)
            })
    }

    fun onProductClick(product: Product) = viewState.showProductDetailed(product)

}

interface CatalogView : MvpView {

    @AddToEndSingle
    fun setProducts(products: List<Product>)

    @OneExecution
    fun showProductDetailed(product: Product)

    @AddToEndSingle
    fun showLoading(isShow: Boolean)
}