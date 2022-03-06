package com.bondidos.clevertectask4.data.atmApi

import com.bondidos.clevertectask4.data.api_models.AtmItem
import retrofit2.http.GET
import retrofit2.http.Query

interface BelBankService {

    @GET("/api/atm")
    suspend fun getAtmList(
        @Query("city") city: String
    ): List<AtmItem>
}