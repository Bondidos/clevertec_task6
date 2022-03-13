package com.bondidos.clevertectask4.domain

import com.bondidos.clevertectask4.data.data_models.AtmItem
import com.bondidos.clevertectask4.data.data_models.InfoBoxItem
import com.bondidos.clevertectask4.data.data_models.Office
import io.reactivex.Observable

interface Repository {
    fun getAtmList(city: String): Observable<List<AtmItem>>
    fun getInfoBoxList(city: String): Observable<List<InfoBoxItem>>
    fun getOffices(city: String): Observable<List<Office>>
}