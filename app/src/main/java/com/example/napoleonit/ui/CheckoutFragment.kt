package com.example.napoleonit.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.napoleonit.R
import com.example.napoleonit.presentation.CheckoutPresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_checkout.*
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.alias.AddToEndSingle
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
        showLoading(false)
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

    override fun showOrderError() =
        showErrorToast("Не получилось создать заказ")

    override fun showNameError() =
        showErrorToast("Имя должно содержать от 2 до 15 символов")

    override fun showSurnameError() =
        showErrorToast("Фамилия должна содержать от 2 до 20 символов")

    override fun showPhoneError() =
        showErrorToast("Номер должен начинаться с 8 или +7 и содержать 11 цифр")

    override fun showEmptyCartError() {
        showErrorToast("Корзина пуста")
    }

    override fun showLoading(isShow: Boolean) {
        loadPb.isVisible = isShow
    }

    private fun showErrorToast(errMsg: String) =
        Toast.makeText(requireContext(), "Ошибка: $errMsg", Toast.LENGTH_LONG).show()
}

interface CheckoutView : MvpView {

    /**
     * Функция показа ошибки ввода в поле имени
     */
    @Skip
    fun showNameError()

    /**
     * Функция показа ошибки ввода в поле фамилии
     */
    @Skip
    fun showSurnameError()

    /**
     * Функция показа ошибки ввода в поле телефона
     */
    @Skip
    fun showPhoneError()

    /**
     * Функция показа ошибки пустой корзины
     */
    @Skip
    fun showEmptyCartError()

    /**
     * Функция показа успешного выполнения заказа
     */
    @Skip
    fun showSuccessfulOrder()

    /**
     * Функция показа ошибки создания заказа
     */
    @Skip
    fun showOrderError()

    /**
     * Функция показа и скрытия индикатора загрузки по переменной [isShow]
     */
    @AddToEndSingle
    fun showLoading(isShow: Boolean)
}
