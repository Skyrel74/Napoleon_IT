package com.example.napoleonit.domain

import com.example.napoleonit.data.entity.OrderResponse
import com.example.napoleonit.di.MainApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class SendOrderUseCase @Inject constructor(
    private val mainApi: MainApi
) {

    suspend operator fun invoke(order: Order) : Response<OrderResponse>? = withContext(Dispatchers.IO) {
        mainApi.sendOrder(order)
    }
}
