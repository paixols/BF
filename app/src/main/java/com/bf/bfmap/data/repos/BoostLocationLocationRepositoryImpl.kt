package com.bf.bfmap.data.repos

import com.bf.bfmap.data.source.BoostLocationDataSource
import com.bf.bfmap.domain.entities.BoostLocation
import com.bf.bfmap.domain.repos.BoostLocationRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/*Repository Implementation makes sure we exchange data
* in a valid format before we push/pull from the data source domain
* */
class BoostLocationLocationRepositoryImpl @Inject constructor(
    private val boostLocationDataSource: BoostLocationDataSource
) : BoostLocationRepository {

    @ExperimentalCoroutinesApi
    override suspend fun saveBoostLocation(location: LatLng) {
        boostLocationDataSource.createBoostLocation(
            mapBoostLocation(latLng = location)
        )
    }

    private fun mapBoostLocation(latLng: LatLng): BoostLocation =
        BoostLocation().also {
            it.lat = latLng.latitude
            it.lng = latLng.longitude
        }

}