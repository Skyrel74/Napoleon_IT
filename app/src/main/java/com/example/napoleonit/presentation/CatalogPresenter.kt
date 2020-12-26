package com.example.napoleonit.presentation

import com.example.napoleonit.data.entity.Product
import com.example.napoleonit.domain.GetAllProductsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenterScope
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

class CatalogPresenter(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : MvpPresenter<CatalogView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading(true)
        presenterScope.launch(CoroutineExceptionHandler { context, throwable ->
            viewState.showLoading(false)
        }) {
            val products = getAllProductsUseCase()
            viewState.setProducts(products)
            viewState.showLoading(false)
        }
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