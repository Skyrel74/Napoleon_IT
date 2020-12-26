package com.example.napoleonit.ui

import android.os.Bundle
import android.view.View
import com.example.napoleonit.R
import com.example.napoleonit.domain.Product
import com.example.napoleonit.presentation.DetailedPresenter
import com.example.napoleonit.presentation.DetailedPresenterFactory
import com.example.napoleonit.presentation.DetailedView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detailed.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class DetailedFragment : MvpAppCompatFragment(R.layout.fragment_detailed), DetailedView {

    @Inject
    lateinit var detailedPresenterFactory: DetailedPresenterFactory

    private val presenter: DetailedPresenter by moxyPresenter {
        detailedPresenterFactory.create(arguments?.getParcelable(DETAILED_TAG)!!)
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
        addToCartBtn.text = if (inCart)
            getString(R.string.delete_from_cart)
        else
            getString(R.string.add_to_cart)
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
