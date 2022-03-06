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

        when(val item = marker.tag){
            is Position -> {
                val view = LayoutInflater.from(context).inflate(
                    R.layout.marker_info_contents, null
                )
                view.findViewById<TextView>(R.id.text_view_title).text =
                    context.getString(R.string.place, item.install_place)
                view.findViewById<TextView>(R.id.text_view_address).text =
                    context.getString(
                        R.string.address_text,
                        item.address_type,
                        item.address,
                        item.house
                    )
                item.ATM_type?.let {
                    view.findViewById<TextView>(R.id.text_view_type).text =
                        context.getString(R.string.type, item.ATM_type)
                }
                view.findViewById<TextView>(R.id.text_work_time).text =
                    context.getString(R.string.work_time, item.work_time)
                view.findViewById<TextView>(R.id.text_point_type).text =
                    context.getString(item.point_type, item.work_time)
                return view
            }
            else -> return null
        }
    }


    override fun getInfoWindow(marker: Marker): View? = null

}