package com.bondidos.clevertectask4.presentation.ui.map_fragment.viewmodel

import androidx.lifecycle.ViewModel
import com.bondidos.clevertectask4.domain.constants.Constants.CITY_HOMEL
import com.bondidos.clevertectask4.domain.useCase.FetchDataUseCase
import com.bondidos.clevertectask4.domain.ui_models.Position
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MapViewModel(fetchData: FetchDataUseCase) :
    ViewModel() {
    val list: Observable<List<Position>> = fetchData.execute(CITY_HOMEL)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

