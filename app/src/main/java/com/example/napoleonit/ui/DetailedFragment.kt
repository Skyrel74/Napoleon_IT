package com.example.napoleonit.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.napoleonit.R
import com.example.napoleonit.data.CartDaoImpl
import com.example.napoleonit.domain.Product
import com.example.napoleonit.presentation.DetailedPresenter
import com.example.napoleonit.presentation.DetailedView
import kotlinx.android.synthetic.main.fragment_detailed.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DetailedFragment : MvpAppCompatFragment(R.layout.fragment_detailed), DetailedView {

    private val presenter: DetailedPresenter by moxyPresenter {
        DetailedPresenter(
            arguments?.getParcelable(DETAILED_TAG)!!,
            CartDaoImpl(
                requireContext().getSharedPreferences("DATA", Context.MODE_PRIVATE)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addToCartBtn.setOnClickListener {
            presenter.onCartClicked()
        }
    }

    override fun setProduct(product: Product) {
        textView.text = "${textView.text}\n${product?.name} ${product?.price}"
    }

    override fun setIsInCart(inCart: Boolean) {
        if (inCart) {
            addToCartBtn.text = "Убрать из корзины"
        } else {
            addToCartBtn.text = "Добавить в корзину"
        }
    }

    companion object {

        private const val DETAILED_TAG = "DETAILED_TAG"

        fun newInstance(product: Product) =
            DetailedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DETAILED_TAG, product)
                }
            }
    }
}
