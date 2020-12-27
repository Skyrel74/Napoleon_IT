package com.example.napoleonit.data

import com.example.napoleonit.domain.Product

interface CartDao {

    /**
     * Функция добавление продукта [product] в корзину
     */
    fun addToCart(product: Product)

    /**
     * Функция удаления продукта [product] из корзины
     */
    fun deleteFromCart(product: Product)

    /**
     * Фукция @return списка продуктов из корзины
     */
    fun getAllFromCart(): List<Product>

    /**
     * Функция очистки корзины
     */
    fun clearCart()

    /**
     * Функция, которая отвечает есть ли [product] в корзине
     * Возвращает @return булево значение
     */
    fun isInCart(product: Product): Boolean
}
