package com.example.napoleonit.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.napoleonit.R
import com.example.napoleonit.domain.Product
import com.example.napoleonit.presentation.CatalogPresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_catalog.*
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import javax.inject.Inject

@AndroidEntryPoint
class CatalogFragment : MvpAppCompatFragment(R.layout.fragment_catalog), CatalogView {

    @Inject
    lateinit var catalogPresenter: CatalogPresenter

    private val presenter: CatalogPresenter by moxyPresenter { catalogPresenter }
    private var catalogAdapter: CatalogAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(productsRv) {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(CharacterItemDecoration(10))
            adapter = CatalogAdapter {
                presenter.onProductClick(it)
            }
                .also { catalogAdapter = it }
        }
    }

    override fun setProducts(products: List<Product>) {
        catalogAdapter?.submitList(products)
    }

    override fun showProductDetailed(product: Product) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, DetailedFragment.newInstance(product))
            .addToBackStack("Catalog")
            .commit()
    }

    override fun showLoading(isShow: Boolean) {
        loadPb.isVisible = isShow
    }

    override fun onDestroyView() {
        super.onDestroyView()
        catalogAdapter = null
    }
}

interface CatalogView : MvpView {

    /**
     * Функция установки продуктов [products] в список каталога
     */
    @AddToEndSingle
    fun setProducts(products: List<Product>)

    /**
     * Функция показа детальной информации о продукте [product]
     */
    @OneExecution
    fun showProductDetailed(product: Product)

    /**
     * Функция показа и скрытия индикатора загрузки по переменной [isShow]
     */
    @AddToEndSingle
    fun showLoading(isShow: Boolean)
}
