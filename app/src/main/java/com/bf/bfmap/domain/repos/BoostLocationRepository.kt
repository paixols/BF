package com.bf.bfmap.domain.repos

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface BoostLocationRepository {
    @ExperimentalCoroutinesApi
    suspend fun saveBoostLocation(location: LatLng)
}