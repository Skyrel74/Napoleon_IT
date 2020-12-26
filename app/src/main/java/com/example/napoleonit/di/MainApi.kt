package com.example.napoleonit.di

import com.example.napoleonit.data.entity.ProductResponse
import retrofit2.http.GET

interface MainApi {

    @GET(".json/")
    suspend fun getAllProducts() : ProductResponse
}