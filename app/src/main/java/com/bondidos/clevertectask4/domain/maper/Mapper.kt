package com.bondidos.clevertectask4.domain.maper

import com.bondidos.clevertectask4.R
import com.bondidos.clevertectask4.data.api_models.AtmItem
import com.bondidos.clevertectask4.data.api_models.InfoBoxItem
import com.bondidos.clevertectask4.data.api_models.Office
import com.bondidos.clevertectask4.domain.ui_model.Position
import com.google.android.gms.maps.model.LatLng

class Mapper {

    fun fromOffice(office: List<Office>) = office.map{ it.toPosition() }

    fun fromAtmItemList(atm: List<AtmItem>) = atm.map { it.toPosition() }

    fun fromInfoboxList(infoBox: List<InfoBoxItem>) = infoBox.map {it.toPosition()}

    private fun Office.toPosition(): Position {
        return Position(
            id = this.filial_id,
            point_type = R.string.bank_office,
            install_place = this.filial_name,
            address_type = this.street_type,
            address = this.street,
            house = this.home_number,
            ATM_type = null,
            work_time = this.info_worktime,
            latLng = LatLng(
                this.GPS_X,
                this.GPS_Y
            )
        )
    }

    private fun InfoBoxItem.toPosition(): Position {
        return Position(
            id = this.info_id,
            point_type = R.string.info_box_type,
            install_place = this.install_place,
            address_type = this.address_type,
            address = this.address,
            house = this.house,
            ATM_type = this.inf_type,
            work_time = this.work_time,
            latLng = LatLng(
                this.gps_x,
                this.gps_y
            )
        )
    }

    private fun AtmItem.toPosition(): Position {
        return Position(
            id = this.id,
            point_type = R.string.atm_type,
            install_place = this.install_place,
            address_type = this.address_type,
            address = this.address,
            house = this.house,
            ATM_type = this.ATM_type,
            work_time = this.work_time,
            latLng = LatLng(
                this.gps_x,
                this.gps_y
            )
        )
    }
}