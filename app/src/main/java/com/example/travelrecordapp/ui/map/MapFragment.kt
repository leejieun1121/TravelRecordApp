package com.example.travelrecordapp.ui.map

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.travelrecordapp.R
import com.example.travelrecordapp.databinding.FragmentMapBinding
import com.example.travelrecordapp.databinding.FragmentScheduleBinding
import com.example.travelrecordapp.ui.home.HomeViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(),OnMapReadyCallback{
    private lateinit var binding: FragmentMapBinding
    private val viewModel: MapViewModel by viewModels()

    private lateinit var mLocationManager: LocationManager
    private lateinit var mLocationListener: LocationListener
    private lateinit var mMap: GoogleMap

    private var lat =0.0
    private var lng =0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val mapFragment = childFragmentManager.findFragmentById(R.id.fragment_google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        //TODO 퍼미션 설정, Location Manager 사용해서 현재위치 넣기
        getCurrentLocation()
        permissionCheck()
        mMap = googleMap
    }

    private fun permissionCheck(){
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mLocationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                3000L,
                30f,
                mLocationListener
            )
        }
    }

    private fun getCurrentLocation(){
        mLocationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
        mLocationListener = object : LocationListener{
            override fun onLocationChanged(location: Location) {
                lat = location.latitude
                lng = location.longitude
                Log.d("tagLocation", "Lat: ${lat}, lon: ${lng}")

                var currentLocation = LatLng(lat, lng)
                mMap.addMarker(MarkerOptions().position(currentLocation).title("현재위치"))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))

            }
        }
    }
}