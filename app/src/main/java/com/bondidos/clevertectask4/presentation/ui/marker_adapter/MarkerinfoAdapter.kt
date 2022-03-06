package com.bondidos.clevertectask4.presentation.ui.marker_adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.bondidos.clevertectask4.R
import com.bondidos.clevertectask4.domain.ui_model.Position
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class MarkerInfoWindowAdapter(
    private val context: Context
) : GoogleMap.InfoWindowAdapter {

    @SuppressLint("InflateParams")
    override fun getInfoContents(marker: Marker): View? {

        val atmItem = marker.tag as? Position ?: return null

        val view = LayoutInflater.from(context).inflate(
            R.layout.marker_info_contents, null
        )
        view.findViewById<TextView>(R.id.text_view_title).text =
            context.getString(R.string.place, atmItem.install_place)
        view.findViewById<TextView>(R.id.text_view_address).text =
            context.getString(
                R.string.address_text,
                atmItem.address_type,
                atmItem.address,
                atmItem.house
            )
        view.findViewById<TextView>(R.id.text_view_type).text =
            context.getString(R.string.type, atmItem.ATM_type)
        view.findViewById<TextView>(R.id.text_work_time).text =
            context.getString(R.string.work_time, atmItem.work_time)

        return view
    }


    override fun getInfoWindow(marker: Marker): View? = null

}