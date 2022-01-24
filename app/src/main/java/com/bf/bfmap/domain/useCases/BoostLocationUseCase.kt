package com.bf.bfmap.domain.useCases

import android.util.Log
import com.bf.bfmap.domain.repos.BoostLocationRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/*Use case can be used to filter or modify and provide a unique data set
* i.e. Filter a location where the service is available, and return an error
* if the service is not available in the area.
* */
class BoostLocationUseCase @Inject constructor(
    private val boostLocationRepository: BoostLocationRepository
) {

    @ExperimentalCoroutinesApi
    suspend fun invoke(location: LatLng) {
        if (serviceIsAvailableInThisArea()) {
            boostLocationRepository.saveBoostLocation(location)
        } else {
            Log.d(TAG, "Service Not Available in the Area")
        }
    }

    /*We assume the service is available everywhere*/
    private fun serviceIsAvailableInThisArea(): Boolean = true

    private companion object {
        val TAG = this::class.simpleName
    }
}