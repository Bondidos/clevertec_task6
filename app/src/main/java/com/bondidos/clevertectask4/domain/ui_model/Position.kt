package com.bondidos.clevertectask4.domain.ui_model

import com.google.android.gms.maps.model.LatLng

data class Position(
    val id: String,
    val install_place: String,
    val address_type: String,
    val address: String,
    val house: String,
    val ATM_type: String,
    val work_time: String,
    val latLng: LatLng
)