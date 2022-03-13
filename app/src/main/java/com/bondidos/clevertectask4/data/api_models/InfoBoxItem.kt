package com.bondidos.clevertectask4.data.api_models

data class InfoBoxItem(
    val address: String,
    val address_type: String,
    val area: String,
    val cash_in: String,
    val cash_in_exist: String,
    val city: String,
    val city_type: String,
    val currency: String,
    val gps_x: Double,
    val gps_y: Double,
    val house: String,
    val inf_printer: String,
    val inf_status: String,
    val inf_type: String,
    val info_id: String,
    val install_place: String,
    val location_name_desc: String,
    val popolnenie_platej: String,
    val region_platej: String,
    val time_long: String,
    val type_cash_in: String,
    val work_time: String
)