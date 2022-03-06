package com.bondidos.clevertectask4.domain.resources_state

import com.bondidos.clevertectask4.domain.ui_model.Position

sealed class Resource {
    object Initialized : Resource()
    data class Success(val data: List<Position>) : Resource()
    class Error(val message: Int) : Resource()
}