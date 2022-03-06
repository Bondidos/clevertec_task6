package com.bondidos.clevertectask4.presentation.ui

import androidx.lifecycle.ViewModel
import com.bondidos.clevertectask4.domain.useCase.GetAtmListUseCase
import com.bondidos.clevertectask4.domain.ui_model.Position
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ActivityViewModel @Inject constructor(getAtmListUseCase: GetAtmListUseCase) :
    ViewModel() {
        val list: Observable<List<Position>> = getAtmListUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

