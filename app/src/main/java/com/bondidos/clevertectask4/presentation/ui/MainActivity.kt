package com.bondidos.clevertectask4.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bondidos.clevertectask4.R
import com.bondidos.clevertectask4.domain.ui_model.Position
import com.bondidos.clevertectask4.presentation.ui.marker_adapter.MarkerInfoWindowAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var viewModel: ActivityViewModel
    private var position: List<Position>? = null
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch()
    }

    private fun launch() {

        disposable = viewModel.list.subscribe(
            {result -> position = result
                createTags()
            },
            {
                    Toast.makeText(
                        applicationContext,
                        applicationContext.getString(R.string.network_error),
                        Toast.LENGTH_SHORT
                    ).show()
            }
        )
    }

    private fun createTags() {
        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(applicationContext))
            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()
                position?.forEach { item ->
                    bounds.include(
                        item.latLng
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
        position?.forEach { item ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(item.install_place)
                    .position(
                        item.latLng
                    )
            )
            marker?.tag = item
        }
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}