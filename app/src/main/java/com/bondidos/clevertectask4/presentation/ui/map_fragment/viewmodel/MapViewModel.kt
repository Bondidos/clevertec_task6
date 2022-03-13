package com.bondidos.clevertectask4.presentation.ui.map_fragment.viewmodel

import androidx.lifecycle.ViewModel
import com.bondidos.clevertectask4.domain.useCase.GetAtmListUseCase
import com.bondidos.clevertectask4.domain.ui_models.Position
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MapViewModel(getAtmListUseCase: GetAtmListUseCase) :
    ViewModel() {
    val list: Observable<List<Position>> = getAtmListUseCase.execute()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

