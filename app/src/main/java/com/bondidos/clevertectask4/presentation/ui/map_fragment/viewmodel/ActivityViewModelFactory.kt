package com.bondidos.clevertectask4.presentation.ui.map_fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bondidos.clevertectask4.domain.useCase.GetAtmListUseCase
import javax.inject.Inject

class ActivityViewModelFactory @Inject constructor(
    private val newsRepository: GetAtmListUseCase,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MapViewModel(newsRepository) as T
    }
}