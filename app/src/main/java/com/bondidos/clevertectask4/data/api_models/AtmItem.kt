package com.bondidos.clevertectask4.data.api_models

data class AtmItem(
    val ATM_error: String,
    val ATM_printer: String,
    val ATM_type: String,
    val address: String,
    val address_type: String,
    val area: String,
    val cash_in: String,
    val city: String,
    val city_type: String,
    val currency: String,
    val gps_x: Double,
    val gps_y: Double,
    val house: String,
    val id: String,
    val install_place: String,
    val install_place_full: String,
    val work_time: String,
    val work_time_full: String
)