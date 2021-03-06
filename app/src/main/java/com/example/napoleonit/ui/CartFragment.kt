package com.example.napoleonit.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.napoleonit.R
import com.example.napoleonit.domain.Product
import com.example.napoleonit.presentation.CartPresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cart.*
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.alias.AddToEndSingle
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : MvpAppCompatFragment(R.layout.fragment_cart), CartView {

    @Inject
    lateinit var cartPresenter : CartPresenter
    private val presenter: CartPresenter by moxyPresenter { cartPresenter }

    private var cartAdapter :CartAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(cartRv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CartAdapter {
                presenter.onDeleteClick(it)
            }
                .also { cartAdapter = it }
        }
    }

    override fun setCart(cart: List<Product>) {
        cartAdapter?.submitList(cart)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cartAdapter = null
    }
}

interface CartView : MvpView {

    /**
     * Функция установки списка продуктов в корзине [cart]
     */
    @AddToEndSingle
    fun setCart(cart: List<Product>)
}
