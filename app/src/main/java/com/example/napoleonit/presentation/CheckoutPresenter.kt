package com.example.napoleonit.presentation

import com.example.napoleonit.data.entity.Order
import com.example.napoleonit.ui.CheckoutView
import com.example.napoleonit.ui.Type
import moxy.MvpPresenter

class CheckoutPresenter : MvpPresenter<CheckoutView>() {

    private var selectedType: Type = Type.CASH

    fun setType(type: Type) {
        selectedType = type
    }

    fun validate(name: String, surname: String, phone: String) {
        when {
            !isNameCorrect(name) -> viewState.showNameError()
            !isSurnameCorrect(surname) -> viewState.showSurnameError()
            !isPhoneCorrect(phone) -> viewState.showPhoneError()
            else -> {
                makeOrder(name, surname, phone, selectedType)
                viewState.showSuccessfulOrder()
            }

        }
    }

    private fun makeOrder(name: String, surname: String, phone: String, selectedType: Type) {
        val newOrder = Order(
            name,
            surname,
            phone,
            selectedType
        )
    }

    private fun isNameCorrect(name: String): Boolean = name.length in 2..15

    private fun isSurnameCorrect(surname: String): Boolean = surname.length in 2..20

    private fun isPhoneCorrect(phone: String): Boolean = when (phone.length) {
        11 -> phone[0] == '8'
        12 -> phone[0] == '+' && phone[1] == '7'
        else -> false
    }
}
