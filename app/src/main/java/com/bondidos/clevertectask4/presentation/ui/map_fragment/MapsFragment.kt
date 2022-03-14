package com.bondidos.clevertectask4.presentation.ui.map_fragment

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bondidos.clevertectask4.R
import com.bondidos.clevertectask4.domain.ui_models.Position
import com.bondidos.clevertectask4.presentation.di.appComponent
import com.bondidos.clevertectask4.presentation.ui.map_fragment.viewmodel.MapViewModelFactory
import com.bondidos.clevertectask4.presentation.ui.map_fragment.viewmodel.MapViewModel
import com.bondidos.clevertectask4.presentation.ui.marker_adapter.MarkerInfoWindowAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MapsFragment : Fragment() {

    @Inject
    lateinit var factory: MapViewModelFactory
    private val viewModel: MapViewModel by viewModels { factory }
    private var position: List<Position> = mutableListOf()
    private var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireContext().appComponent.inject(this)
        launch()
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    private fun launch() {

        disposable = viewModel.list.subscribe(
            { result ->
                position = result
                createTags()
            },
            {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.network_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

    private fun createTags() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment?.getMapAsync { googleMap ->
            googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(requireContext()))
            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()
                position.forEach { item ->
                    bounds.include(
                        item.latLng
                    )
                }
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngBounds(bounds.build(), 150)
                )
            }
            addMarkers(googleMap)
        }
    }

    private fun addMarkers(googleMap: GoogleMap) {
        position.forEach { item ->
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