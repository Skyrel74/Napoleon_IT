package com.example.napoleonit.domain

import com.example.napoleonit.data.entity.Product
import com.example.napoleonit.di.MainApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllProductsUseCase(private val mainApi: MainApi) {

    suspend operator fun invoke(): List<Product> = withContext(Dispatchers.IO) {
        mainApi.getAllProducts().run {
            products?.mapNotNull {
                Product(
                    name = it.name ?: return@mapNotNull null,
                    price = it.price ?: return@mapNotNull null,
                    discount = it.discount ?: return@mapNotNull null,
                    img = it.img ?: return@mapNotNull null,
                    description = it.description ?: return@mapNotNull null
                )
            } ?: emptyList()
        }
    }
}
