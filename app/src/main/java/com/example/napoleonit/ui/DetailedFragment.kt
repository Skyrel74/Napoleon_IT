package com.example.napoleonit.ui

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.napoleonit.MainActivity
import com.example.napoleonit.R
import com.example.napoleonit.domain.Product
import com.example.napoleonit.presentation.DetailedPresenter
import com.example.napoleonit.presentation.DetailedPresenterFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detailed.*
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.alias.AddToEndSingle
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

        (activity as MainActivity).hideTable()
    }

    override fun setProduct(product: Product) {
        Glide.with(requireContext()).load(product.img).into(detailedIv)
        detailedNameTv.text = product.name
        detailedPriceTv.text = product.calcDiscountPrice().toString()
        addToCartBtn.setOnClickListener {
            presenter.onCartClicked()
        }
        detailedDescriptionTv.text = product.description
    }

    override fun setIsInCart(inCart: Boolean) {
        addToCartBtn.text = if (inCart)
            getString(R.string.delete_from_cart)
        else
            getString(R.string.add_to_cart)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as MainActivity).showTable()
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

interface DetailedView : MvpView {

    /**
     * Функция установки полей продукта [product]
     */
    @AddToEndSingle
    fun setProduct(product: Product)

    /**
     * Функция изменения текста кноки по значению переменной [inCart]
     */
    @AddToEndSingle
    fun setIsInCart(inCart: Boolean)
}
