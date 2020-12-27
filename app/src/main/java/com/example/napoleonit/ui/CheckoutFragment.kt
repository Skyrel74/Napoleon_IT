package com.example.napoleonit.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.napoleonit.R
import com.example.napoleonit.presentation.CheckoutPresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_checkout.*
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.alias.Skip
import javax.inject.Inject

enum class Type { CARD, CASH }

@AndroidEntryPoint
class CheckoutFragment : MvpAppCompatFragment(R.layout.fragment_checkout), CheckoutView {

    @Inject
    lateinit var checkoutPresenter: CheckoutPresenter
    private val presenter : CheckoutPresenter by moxyPresenter { checkoutPresenter }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        payBtn.setOnClickListener {
            presenter.validate(
                nameEt.text.toString(),
                surnameEt.text.toString(),
                phoneEt.text.toString()
            )
        }

        card.isChecked = true
        rgType.setOnCheckedChangeListener { _, i ->
            val selectedType = when (i) {
                R.id.card -> Type.CARD
                else -> Type.CASH
            }

            presenter.setType(selectedType)
        }
    }

    override fun showSuccessfulOrder() =
        Toast.makeText(requireContext(), "Покупка успешна", Toast.LENGTH_LONG).show()

    override fun showNameError() =
        showErrorToast("Имя должно содержать от 2 до 15 символов")

    override fun showSurnameError() =
        showErrorToast("Фамилия должна содержать от 2 до 20 символов")

    override fun showPhoneError() =
        showErrorToast("Номер должен начинаться с 8 или +7 и содержать 11 цифр")

    private fun showErrorToast(errMsg: String) =
        Toast.makeText(requireContext(), "Ошибка: $errMsg", Toast.LENGTH_LONG).show()
}

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
