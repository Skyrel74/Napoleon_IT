package com.example.napoleonit.presentation

import com.example.napoleonit.data.CartDao
import com.example.napoleonit.domain.SendOrderUseCase
import com.example.napoleonit.extentions.launchWithErrorHandler
import com.example.napoleonit.ui.CheckoutView
import com.example.napoleonit.ui.Type
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class CheckoutPresenter @Inject constructor(
    private val cartDao: CartDao,
    private val sendOrderUseCase: SendOrderUseCase
) : MvpPresenter<CheckoutView>() {

    private var selectedType: Type = Type.CASH

    fun setType(type: Type) {
        selectedType = type
    }

    fun validate(name: String, surname: String, phone: String) {
        when {
            !isNameCorrect(name) -> viewState.showNameError()
            !isSurnameCorrect(surname) -> viewState.showSurnameError()
            !isPhoneCorrect(phone) -> viewState.showPhoneError()
            cartDao.getAllFromCart().isEmpty() -> viewState.showEmptyCartError()
            else -> {
                makeOrder(name, surname, phone, selectedType)
                viewState.showSuccessfulOrder()
            }
        }
    }

    private fun makeOrder(name: String, surname: String, phone: String, selectedType: Type) {
        viewState.showLoading(true)
        presenterScope.launchWithErrorHandler(
            block = {
                val newOrder = Order(
                    name,
                    surname,
                    phone,
                    selectedType,
                    cartDao.getAllFromCart()
                )
                if (sendOrderUseCase(newOrder) != null) {
                    cartDao.clearCart()
                    viewState.showLoading(false)
                    viewState.showSuccessfulOrder()
                } else {
                    viewState.showLoading(false)
                    viewState.showOrderError()
                }
            }, onError = {
                viewState.showLoading(false)
                viewState.showOrderError()
            })
    }

    private fun isNameCorrect(name: String): Boolean = name.length in 2..15

    private fun isSurnameCorrect(surname: String): Boolean = surname.length in 2..20

    private fun isPhoneCorrect(phone: String): Boolean = when (phone.length) {
        11 -> phone[0] == '8'
        12 -> phone[0] == '+' && phone[1] == '7'
        else -> false
    }
}
