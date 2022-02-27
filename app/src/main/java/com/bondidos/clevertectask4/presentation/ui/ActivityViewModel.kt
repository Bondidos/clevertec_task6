package com.bondidos.clevertectask4.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.clevertectask4.domain.useCase.GetAtmListUseCase
import com.bondidos.clevertectask4.domain.resources_state.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ActivityViewModel @Inject constructor(private val getAtmListUseCase: GetAtmListUseCase) :
    ViewModel() {

    private val _atmList = MutableStateFlow<Resource>(Resource.Initialized)
    val atmList: StateFlow<Resource> = _atmList

    init {
        viewModelScope.launch {
            _atmList.value = getAtmListUseCase.execute()
        }
    }

}

