package com.bondidos.clevertectask4.domain.maper

import com.bondidos.clevertectask4.data.api_models.AtmItem
import com.bondidos.clevertectask4.domain.ui_model.Position
import com.google.android.gms.maps.model.LatLng

class Maper {

    fun fromAtmItemList(atm: List<AtmItem>) = atm.map{
            atmItem -> atmItemToPosition(atmItem)
    }

    private fun atmItemToPosition(item: AtmItem): Position {
        return Position(
            id = item.id,
            install_place = item.install_place,
            address_type = item.address_type,
            address = item.address,
            house = item.house,
            ATM_type = item.ATM_type,
            work_time = item.work_time,
            latLng = LatLng(
                item.gps_x,
                item.gps_y
            )
        )
    }
}