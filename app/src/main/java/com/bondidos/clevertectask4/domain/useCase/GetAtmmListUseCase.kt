package com.bondidos.clevertectask4.domain.useCase

import com.bondidos.clevertectask4.R
import com.bondidos.clevertectask4.data.atmApi.BelBankService
import com.bondidos.clevertectask4.domain.Constants.CITY_HOMEL
import com.bondidos.clevertectask4.domain.maper.Maper
import com.bondidos.clevertectask4.domain.resources_state.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class GetAtmListUseCase @Inject constructor(private val apiService: BelBankService) {
    suspend fun execute(): Resource {
        return withContext(Dispatchers.IO) {
            try {
                val maper = Maper()
                val apiAtmList = apiService.getAtmList(CITY_HOMEL)
                val uiModelList = maper.fromAtmItemList(apiAtmList)
                Resource.Success(uiModelList)
            } catch (e: Exception) {
                Resource.Error(R.string.network_error)
            }
        }
    }
}