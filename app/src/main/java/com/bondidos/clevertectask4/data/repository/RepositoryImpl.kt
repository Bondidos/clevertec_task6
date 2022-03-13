package com.bondidos.clevertectask4.data.repository

import com.bondidos.clevertectask4.data.data_models.AtmItem
import com.bondidos.clevertectask4.data.data_models.InfoBoxItem
import com.bondidos.clevertectask4.data.data_models.Office
import com.bondidos.clevertectask4.data.atmApi.BelBankService
import com.bondidos.clevertectask4.domain.Repository
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val belBankService: BelBankService
    ) : Repository {

    override fun getAtmList(city: String): Observable<List<AtmItem>> =
        belBankService.getAtmList(city)

    override fun getInfoBoxList(city: String): Observable<List<InfoBoxItem>> =
        belBankService.getInfoBoxList(city)

    override fun getOffices(city: String): Observable<List<Office>> =
        belBankService.getOffices(city)
}