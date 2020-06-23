package com.example.restareareview

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*

class MapActivity : AppCompatActivity() {

    var fusedLocationClient: FusedLocationProviderClient?=null
    var locationCallback: LocationCallback?=null
    var locationRequest: LocationRequest?=null

    lateinit var googleMap: GoogleMap
    var loc = LatLng(37.5408, 127.0793)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        setActionBar()
        initLocation()
    }

    fun initLocation(){
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            getuserlocation()
            startLocationUpdates()
            initmap()
        } else{
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION), 100)
        }
    }

    @SuppressLint("MissingPermission")
    fun getuserlocation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient?.lastLocation?.addOnSuccessListener {
            loc = LatLng(it.latitude, it.longitude)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==100){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED &&
                grantResults[1]==PackageManager.PERMISSION_GRANTED){
                getuserlocation()
                startLocationUpdates()
                initmap()
            }else{
                Toast.makeText(this, "위치정보 제공을 하셔야 합니다.", Toast.LENGTH_SHORT).show()
                initmap()
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        locationRequest = LocationRequest.create()?.apply{
            interval = 20000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locationCallback = object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?){
                locationResult?:return
                for(location in locationResult.locations){
                    loc = LatLng(location.latitude, location.longitude)
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f))
                }
            }
        }
        fusedLocationClient?.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    fun stopLocationUpdates(){
        fusedLocationClient?.removeLocationUpdates(locationCallback)
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private fun setActionBar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)

        val view = supportActionBar!!.customView
        val homeImageButton = view.findViewById<ImageButton>(R.id.home)
        homeImageButton.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK))
        }

        val questionImageButton = view.findViewById<ImageButton>(R.id.mail)
        questionImageButton.setOnClickListener {
            val questionIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jackoss@naver.com", null))
            questionIntent.putExtra(Intent.EXTRA_SUBJECT, "")
            questionIntent.putExtra(Intent.EXTRA_TEXT, "")
            startActivity(Intent.createChooser(questionIntent, ""))
        }

        title_text.text = "지도로 찾기"
    }

    fun initmap(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync{
            googleMap = it
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f))
            googleMap.setMinZoomPreference(7.0f)
            googleMap.setMaxZoomPreference(18.0f)

            googleMap.addMarker(MarkerOptions().position(LatLng(37.0000, 127.0000)).title("My mark"))
        }

    }

}