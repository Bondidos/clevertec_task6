package com.bondidos.clevertectask4.domain.resources_state

import com.bondidos.clevertectask4.domain.model.AtmItem

sealed class Resource {
    object Initialized : Resource()
    data class Success(val data: List<AtmItem>) : Resource()
    class Error(val message: Int) : Resource()
}