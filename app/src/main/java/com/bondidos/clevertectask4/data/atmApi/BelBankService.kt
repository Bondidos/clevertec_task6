package com.bondidos.clevertectask4.data.atmApi

import com.bondidos.clevertectask4.data.data_models.AtmItem
import com.bondidos.clevertectask4.data.data_models.InfoBoxItem
import com.bondidos.clevertectask4.data.data_models.Office
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BelBankService {

    @GET("/api/atm")
    fun getAtmList(
        @Query("city") city: String
    ): Observable<List<AtmItem>>

    @GET("/api/infobox")
    fun getInfoBoxList(
        @Query("city") city: String
    ): Observable<List<InfoBoxItem>>

    @GET("/api/filials_info")
    fun getOffices(
        @Query("city") city: String
    ): Observable<List<Office>>
}
