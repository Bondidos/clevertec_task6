package com.bondidos.clevertectask4.domain.useCase

import com.bondidos.clevertectask4.data.atmApi.BelBankService
import com.bondidos.clevertectask4.domain.Constants.CITY_HOMEL
import com.bondidos.clevertectask4.domain.maper.Mapper
import com.bondidos.clevertectask4.domain.ui_model.Position
import io.reactivex.Observable
import javax.inject.Inject

class GetAtmListUseCase @Inject constructor(private val apiService: BelBankService) {
    fun execute(): Observable<List<Position>> {
        val mapper = Mapper()
        val offices = apiService.getOffices(CITY_HOMEL)
            .map {
                mapper.fromOffice(it)
            }
        val infoBoxes = apiService.getInfoBoxList(CITY_HOMEL)
            .map {
                mapper.fromInfoboxList(it)
            }
        val atm = apiService.getAtmList(CITY_HOMEL)
            .map {
                mapper.fromAtmItemList(it)
            }
        return Observable.merge(listOf(atm,infoBoxes,offices))
    }
}