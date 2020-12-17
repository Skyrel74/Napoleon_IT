package com.example.napoleonit.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

interface CheckoutView : MvpView {

    @Skip
    fun showNameError()

    @Skip
    fun showSurnameError()

    @Skip
    fun showPhoneError()

    @Skip
    fun showSuccessfulOrder()
}