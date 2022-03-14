package com.bondidos.clevertectask4.domain.useCase

import com.bondidos.clevertectask4.domain.Repository
import com.bondidos.clevertectask4.domain.utils.Mapper
import com.bondidos.clevertectask4.domain.ui_models.Position
import io.reactivex.Observable
import io.reactivex.functions.Function3
import javax.inject.Inject

class FetchDataUseCase @Inject constructor(private val repository: Repository) {
    fun execute(city: String): Observable<List<Position>> {
        val mapper = Mapper()
        val offices = repository.getOffices(city)
            .map { mapper.officeToPosition(it) }
        val infoBoxes = repository.getInfoBoxList(city)
            .map { mapper.infoBoxToPosition(it) }
        val atm = repository.getAtmList(city)
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