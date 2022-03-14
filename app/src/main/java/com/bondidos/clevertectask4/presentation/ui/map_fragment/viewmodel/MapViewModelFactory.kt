package com.bondidos.clevertectask4.presentation.ui.map_fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bondidos.clevertectask4.domain.useCase.FetchDataUseCase
import javax.inject.Inject

class MapViewModelFactory @Inject constructor(
    private val fetchData: FetchDataUseCase,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MapViewModel(fetchData) as T
    }
}