package com.example.napoleonit.presentation

import com.example.napoleonit.domain.Product
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

class CatalogPresenter : MvpPresenter<CatalogView>() {

    private val products: List<Product> = listOf(
        Product(
            "Ноутбук",
            25000.0,
            img = "https://avatars.mds.yandex.net/get-mpic/1578067/img_id6283543209476248607.jpeg/orig"
        ),
        Product(
            "Гитара",
            9999.0,
            img = "https://3tone.me/upload/iblock/ca8/ca8f285aeab72cbd5eea0b4a9c6bbde1.jpeg"
        ),
        Product(
            "Наушники",
            999.0,
            img = "https://www.sony.ru/image/ac123cd3752c08bcfa9433ea41886e78?fmt=pjpeg&wid=330&bgcolor=FFFFFF&bgc=FFFFFF"
        ),
        Product(
            "Подушка",
            599.0,
            img = "https://www.onlinetrade.ru/img/items/m/marfa_podushkina_legkost_mplg10_5.1_68kh68_122407_2.jpg"
        ),
        Product("Рубашка", 959.0, img = "https://img1.wbstatic.net/big/new/5310000/5315031-1.jpg")
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setProducts(products)
    }

    fun onProductClick(product: Product) = viewState.showProductDetailed(product)

}

interface CatalogView : MvpView {

    @AddToEndSingle
    fun setProducts(products: List<Product>)

    @OneExecution
    fun showProductDetailed(product: Product)

    @OneExecution
    fun showCart()
}