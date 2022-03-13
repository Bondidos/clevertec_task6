package com.bondidos.clevertectask4.domain.useCase

import com.bondidos.clevertectask4.data.repository.RepositoryImpl
import com.bondidos.clevertectask4.domain.constants.Constants.CITY_HOMEL
import com.bondidos.clevertectask4.domain.utils.Mapper
import com.bondidos.clevertectask4.domain.ui_models.Position
import io.reactivex.Observable
import io.reactivex.functions.Function3
import javax.inject.Inject

class GetAtmListUseCase @Inject constructor(private val repositoryImpl: RepositoryImpl) {
    fun execute(): Observable<List<Position>> {
        val mapper = Mapper()
        val offices = repositoryImpl.getOffices(CITY_HOMEL)
            .map { mapper.officeToPosition(it) }
        val infoBoxes = repositoryImpl.getInfoBoxList(CITY_HOMEL)
            .map { mapper.infoBoxToPosition(it) }
        val atm = repositoryImpl.getAtmList(CITY_HOMEL)
            .map { mapper.atmToPosition(it) }

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