package com.example.napoleonit.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.napoleonit.R
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment(R.layout.fragment_cart) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkoutBtn.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.container, CheckoutFragment())
                .addToBackStack("Checkout")
                .commit()
        }
    }
}