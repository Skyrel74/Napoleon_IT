package com.example.napoleonit.di

import com.example.napoleonit.data.entity.OrderResponse
import com.example.napoleonit.data.entity.ProductResponse
import com.example.napoleonit.domain.Order
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainApi {

    @GET(".json/")
    suspend fun getAllProducts(): ProductResponse

    @POST("orders/.json/")
    suspend fun sendOrder(@Body order: Order): Response<OrderResponse>?
}
