package com.example.napoleonit.di

import android.content.Context
import com.example.napoleonit.data.CartDao
import com.example.napoleonit.data.CartDaoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class CartDaoModule {

    @Provides
    @Singleton
    fun provideCartDao(@ApplicationContext context: Context): CartDao = CartDaoImpl(
        context.getSharedPreferences("DATA", Context.MODE_PRIVATE)
    )
}
