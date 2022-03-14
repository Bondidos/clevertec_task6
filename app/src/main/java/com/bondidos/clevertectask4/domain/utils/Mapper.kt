package com.bondidos.clevertectask4.domain.utils

import com.bondidos.clevertectask4.R
import com.bondidos.clevertectask4.data.data_models.AtmItem
import com.bondidos.clevertectask4.data.data_models.InfoBoxItem
import com.bondidos.clevertectask4.data.data_models.Office
import com.bondidos.clevertectask4.domain.ui_models.Position
import com.google.android.gms.maps.model.LatLng
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class Mapper {

    private val anchor = LatLng(52.425163, 31.015039)

    fun officeToPosition(office: List<Office>) = office.map { it.toPosition() }

    fun atmToPosition(atm: List<AtmItem>) = atm.map { it.toPosition() }

    fun infoBoxToPosition(infoBox: List<InfoBoxItem>) = infoBox.map { it.toPosition() }

    private fun Office.toPosition(): Position {
        return Position(
            id = filial_id,
            point_type = R.string.bank_office,
            install_place = filial_name,
            address_type = street_type,
            address = street,
            house = home_number,
            ATM_type = null,
            work_time = info_worktime,
            latLng = LatLng(
                this.GPS_X,
                this.GPS_Y
            ),
            distanceToAnchor = distanceToAnchor(GPS_X, GPS_Y)
        )
    }

    private fun InfoBoxItem.toPosition(): Position {
        return Position(
            id = info_id,
            point_type = R.string.info_box_type,
            install_place = install_place,
            address_type = address_type,
            address = address,
            house = house,
            ATM_type = inf_type,
            work_time = work_time,
            latLng = LatLng(
                gps_x,
                gps_y
            ),
            distanceToAnchor = distanceToAnchor(gps_x, gps_y)
        )
    }

    private fun AtmItem.toPosition(): Position {
        return Position(
            id = id,
            point_type = R.string.atm_type,
            install_place = install_place,
            address_type = address_type,
            address = address,
            house = house,
            ATM_type = ATM_type,
            work_time = work_time,
            latLng = LatLng(
                gps_x,
                gps_y
            ),
            distanceToAnchor = distanceToAnchor(gps_x, gps_y)
        )
    }

    private fun distanceToAnchor(lat: Double, lng: Double) = sqrt(
        (abs(lat) - abs(anchor.latitude)).pow(2.0)
                + (abs(lng) - abs(anchor.longitude)).pow(2.0)
    )
}