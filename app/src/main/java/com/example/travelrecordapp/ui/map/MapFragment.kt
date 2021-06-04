package com.example.travelrecordapp.ui.map

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.travelrecordapp.R
import com.example.travelrecordapp.databinding.FragmentMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar


class MapFragment : Fragment(),OnMapReadyCallback{
    private lateinit var binding: FragmentMapBinding
    private val viewModel: MapViewModel by viewModels()

    private lateinit var mLocationManager: LocationManager
    private lateinit var mLocationListener: LocationListener
    private lateinit var mMap: GoogleMap

    private lateinit var layout : View
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
        //TODO 퍼미션 설정
        mMap = googleMap
        layout = binding.layout
        //맨처음 구글 지도 load 할때 현재 위치 찾는 시간이 좀 걸리기 때문에 설정을 안해주면 아프리카에 있음 ㅎ
        setDefaultLocation()
        getCurrentLocation()
        permissionCheck() //mLocationManager 초기화 때문에 나중에 호출
    }

    private fun permissionCheck(){
        if (ContextCompat.checkSelfPermission(requireContext(), ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(requireContext(), ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                3000L,
                30f,
                mLocationListener
            )
        } else{
            requestMultiplePermissions.launch(
                arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION))

        }
    }

    @SuppressLint("MissingPermission")
    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                Log.d("tag", "${it.key} = ${it.value}")
            }
            if (permissions[ACCESS_COARSE_LOCATION] == true && permissions[ACCESS_FINE_LOCATION] == true) {
                mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    3000L,
                    30f,
                    mLocationListener
                )

            } else {
                val snackBar = Snackbar.make(layout, "권한이 없습니다. 해당 권한을 얻기 위해 설정으로 이동합니다.", Snackbar.LENGTH_INDEFINITE)
                snackBar.setAction("확인",SettingClickListener())
                snackBar.show()
            }
        }


    private fun getCurrentLocation(){
        mLocationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
        mLocationListener = object : LocationListener{
            override fun onLocationChanged(location: Location) {
                lat = location.latitude
                lng = location.longitude
                Log.d("tagLocation", "Lat: ${lat}, lon: ${lng}")

                val currentLocation = LatLng(lat, lng)
                mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).position(currentLocation).title("현재위치입니다.")).showInfoWindow()
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
                //animateCamera -> 슬로우모션처럼 위치 이동됨
                //moveCamera -> 바로 이동

            }
        }
    }

    fun setDefaultLocation() {
        //디폴트 위치, Seoul
        val DEFAULT_LOCATION = LatLng(37.56, 126.97)
        mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).position(DEFAULT_LOCATION).title("위치정보 가져올 수 없음").snippet("위치 퍼미션과 GPS를 확인하세요")).showInfoWindow()
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15f)
        mMap.moveCamera(cameraUpdate)
    }

}