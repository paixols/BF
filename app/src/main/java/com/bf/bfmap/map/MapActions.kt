package com.bf.bfmap.map

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

interface MapActions {
    fun setMapType()
    fun setCameraPosition()
    fun getMarkerOptions(latLng: LatLng): MarkerOptions
    fun setMapActions()
}