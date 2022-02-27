package com.bondidos.clevertectask4.data.atmApi

import com.bondidos.clevertectask4.domain.model.AtmItem
import retrofit2.http.GET

interface BelBankService {

    @GET("/api/atm?city=Гомель")
    suspend fun getAtmList(): List<AtmItem>
}