package com.bondidos.clevertectask4.presentation.di

import com.bondidos.clevertectask4.data.atmApi.BelBankService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://belarusbank.by"

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideBelBankApi(): BelBankService {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(BelBankService::class.java)
    }
}