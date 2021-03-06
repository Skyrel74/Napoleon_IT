package com.example.napoleonit

import android.os.Bundle
import android.view.View
import com.example.napoleonit.ui.CartFragment
import com.example.napoleonit.ui.CatalogFragment
import com.example.napoleonit.ui.CheckoutFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AndroidEntryPoint
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

    fun hideTable() {
        navigation.visibility = View.GONE
    }

    fun showTable() {
        navigation.visibility = View.VISIBLE
    }
}

interface MainView : MvpView {

    /**
     * Функция установки начального фрагмента
     */
    @AddToEndSingle
    fun setStartupFragment(savedInstanceState: Bundle?)

    /**
     * Функция установки навигации
     */
    @AddToEndSingle
    fun setNavigation()
}
