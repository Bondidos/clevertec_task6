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
        return apiService.getAtmList(CITY_HOMEL)
            .map {
                mapper.fromAtmItemList(it)
            }
    }
}