package com.example.napoleonit.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.napoleonit.R
import com.example.napoleonit.domain.Product
import com.example.napoleonit.presentation.CatalogPresenter
import kotlinx.android.synthetic.main.fragment_product_catalog.*
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

class CatalogFragment : MvpAppCompatFragment(R.layout.fragment_product_catalog), CatalogView {

    private val presenter: CatalogPresenter by moxyPresenter { CatalogPresenter() }
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


        cartBtn.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.container, CartFragment())
                .addToBackStack("Checkout")
                .commit()
        }
    }

    override fun setProducts(products: List<Product>) {
        catalogAdapter?.submitList(products)
    }

    override fun showProductDetailed(product: Product) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, DetailsFragment.newInstance(product))
            .addToBackStack("Catalog")
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        catalogAdapter = null
    }
}

interface CatalogView : MvpView {

    @AddToEndSingle
    fun setProducts(products: List<Product>)

    @OneExecution
    fun showProductDetailed(product: Product)
}
