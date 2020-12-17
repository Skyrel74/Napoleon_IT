package com.example.napoleonit.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.napoleonit.Product
import com.example.napoleonit.R
import kotlinx.android.synthetic.main.fragment_product_catalog.*

class CatalogFragment : Fragment(R.layout.fragment_product_catalog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = Product("Мяч", 800.0)

        detailedBtn.setOnClickListener {
            requireFragmentManager().beginTransaction()
                    .replace(R.id.container, DetailsFragment.newInstance(product))
                    .addToBackStack("Catalog")
                    .commit()
        }

        cartBtn.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.container, CartFragment())
                .addToBackStack("Checkout")
                .commit()
        }
    }
}