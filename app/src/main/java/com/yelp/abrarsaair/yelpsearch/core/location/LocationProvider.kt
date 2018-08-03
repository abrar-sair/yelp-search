package com.yelp.abrarsaair.yelpsearch.core.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.requestPermissions

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yelp.abrarsaair.yelpsearch.Const

class LocationProvider private constructor(private val context: Context) {

    var currentLocation: Location? = null
    private val mFusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun startLocationTask(callBack: LocationCallBack) {
        mFusedLocationClient.lastLocation.addOnSuccessListener(context as Activity) { location ->
            currentLocation = location
            callBack.locationCallBack(location)
        }
    }

    fun startLocationTask() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION),Const.REQUEST_CODE);
            return
        }
        mFusedLocationClient.lastLocation.addOnSuccessListener(context as Activity) { location -> currentLocation = location }
    }


    fun startLocationListener() {
        startLocationTask()
    }

    companion object {

        private var instance: LocationProvider? = null

        @Synchronized
        fun getInstance(context: Context): LocationProvider {
            if (instance == null) {
                instance = LocationProvider(context)
            }
            return instance as LocationProvider
        }
    }

}
