package com.example.napoleonit.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

private const val PROJECT_ID = "syncopa-57e9d-default-rtdb"

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideMainApi(): MainApi = Retrofit.Builder()
        .baseUrl("https://$PROJECT_ID.firebaseio.com/")
        .client(OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }.build())
        .addConverterFactory(Json(builderAction = {
            ignoreUnknownKeys = true
        }).asConverterFactory("application/json".toMediaType()))
        .build()
        .create()
}

