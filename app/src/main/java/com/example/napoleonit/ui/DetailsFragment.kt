package com.example.napoleonit.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.napoleonit.Product
import com.example.napoleonit.R
import kotlinx.android.synthetic.main.fragment_product_details.*

class DetailsFragment : Fragment(R.layout.fragment_product_details) {

    companion object {

        private const val PRODUCT_TAG = "PRODUCT_TAG"

        fun newInstance(product: Product) =
                DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(PRODUCT_TAG, product)
                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val product = it.getParcelable<Product>(PRODUCT_TAG)

            textView.text = "${textView.text}\n${product?.name} ${product?.price}"
        }
    }
}
