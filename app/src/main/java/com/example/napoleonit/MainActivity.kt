package com.example.napoleonit

import android.os.Bundle
import com.example.napoleonit.ui.CartFragment
import com.example.napoleonit.ui.CatalogFragment
import com.example.napoleonit.ui.CheckoutFragment
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

class MainActivity : MvpAppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStartupFragment(savedInstanceState)
        setNavigation()
    }

    override fun setStartupFragment(savedInstanceState: Bundle?) {

        if (savedInstanceState == null)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, CatalogFragment())
            .commit()
    }

    override fun setNavigation() {

        catalogBtn.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CatalogFragment())
                    .commit()
        }
        cartBtn.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CartFragment())
                    .commit()
        }
        checkoutBtn.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CheckoutFragment())
                    .commit()
        }
    }
}

interface MainView : MvpView {

    @AddToEndSingle
    fun setStartupFragment(savedInstanceState: Bundle?)

    @AddToEndSingle
    fun setNavigation()
}