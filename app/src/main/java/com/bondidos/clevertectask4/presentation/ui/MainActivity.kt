package com.bondidos.clevertectask4.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bondidos.clevertectask4.R
import com.bondidos.clevertectask4.domain.model.AtmItem
import com.bondidos.clevertectask4.domain.resources_state.Resource
import com.bondidos.clevertectask4.presentation.ui.marker_adapter.MarkerInfoWindowAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var viewModel: ActivityViewModel
    private var atmList: List<AtmItem>? = null
    private val mainScope = CoroutineScope(Job() + Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch()
    }

    private fun launch() {
        mainScope.launch {
            viewModel.atmList.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        atmList = resource.data
                        createTags()
                    }
                    is Resource.Error ->
                        Toast.makeText(
                            applicationContext,
                            applicationContext.getString(resource.message),
                            Toast.LENGTH_SHORT
                        ).show()
                    else -> Unit
                }
            }
        }
    }

    private fun createTags() {
        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(applicationContext))
            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()
                atmList?.forEach { atmItem ->
                    bounds.include(
                        LatLng(atmItem.gps_x, atmItem.gps_y)
                    )
                }
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngBounds(bounds.build(), 20)
                )
            }
            addMarkers(googleMap)
        }
    }

    private fun addMarkers(googleMap: GoogleMap) {
        atmList?.forEach { item ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(item.install_place)
                    .position(
                        LatLng(
                            item.gps_x,
                            item.gps_y
                        )
                    )
            )
            marker?.tag = item
        }
    }

    override fun onDestroy() {
        mainScope.cancel()
        super.onDestroy()
    }
}