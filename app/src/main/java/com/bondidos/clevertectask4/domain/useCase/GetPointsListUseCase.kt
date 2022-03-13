package com.bondidos.clevertectask4.domain.useCase

import com.bondidos.clevertectask4.data.atmApi.BelBankService
import com.bondidos.clevertectask4.domain.Constants.CITY_HOMEL
import com.bondidos.clevertectask4.domain.maper.Mapper
import com.bondidos.clevertectask4.domain.ui_model.Position
import io.reactivex.Observable
import io.reactivex.functions.Function3
import javax.inject.Inject

class GetAtmListUseCase @Inject constructor(private val apiService: BelBankService) {
    fun execute(): Observable<List<Position>> {
        val mapper = Mapper()
        val offices = apiService.getOffices(CITY_HOMEL)
            .map { mapper.fromOffice(it) }
        val infoBoxes = apiService.getInfoBoxList(CITY_HOMEL)
            .map { mapper.fromInfoboxList(it) }
        val atm = apiService.getAtmList(CITY_HOMEL)
            .map { mapper.fromAtmItemList(it) }

        return Observable.zip( atm, infoBoxes, offices,
            Function3<List<Position>, List<Position>, List<Position>, List<Position>> { o1, o2, o3 ->
                val result = mutableListOf<Position>().apply {
                    addAll(o1)
                    addAll(o2)
                    addAll(o3)
                    sortBy { it.distanceToAnchor }
                }

                return@Function3 result.filter { result.indexOf(it) < 10 }
            }
        )
    }
}