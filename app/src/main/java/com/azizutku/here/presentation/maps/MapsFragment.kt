package com.azizutku.here.presentation.maps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.azizutku.here.R
import com.azizutku.here.databinding.MapsFragmentBinding
import com.azizutku.here.databinding.SignInFragmentBinding
import com.azizutku.here.util.BitmapHelper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment(), OnMapReadyCallback {

    companion object {
        fun newInstance() = MapsFragment()
    }

    private lateinit var binding: MapsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addGoogleMaps()
        binding = DataBindingUtil.inflate(inflater, R.layout.maps_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun addGoogleMaps() {
        val mapFragment = SupportMapFragment.newInstance()
        childFragmentManager
            .beginTransaction()
            .add(R.id.map, mapFragment)
            .commit()
        mapFragment.getMapAsync(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val markerIcon = BitmapHelper.vectorToBitmap(
            requireContext(),
            R.drawable.ic_baseline_map_marker_24,
            getColor(requireContext(), R.color.colorRed)
        )

        val markerIcon2 = BitmapHelper.vectorToBitmap(
            requireContext(),
            R.drawable.ic_baseline_map_marker_24,
            getColor(requireContext(), R.color.colorRed),
            2f
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(41.015137, 28.979530))
                .title("AKUT Kordinasyon")
                .icon(markerIcon)
        )
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(41.515137, 29.479530))
                .title("AKUT Kıyafet Yardımı")
                .icon(markerIcon)
        )
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
            LatLng(41.015137, 28.979530),
            15f,
            )
        )
    }

}