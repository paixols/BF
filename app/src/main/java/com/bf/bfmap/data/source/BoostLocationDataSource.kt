package com.bf.bfmap.data.source

import com.bf.bfmap.db.BFDatabase
import com.bf.bfmap.domain.entities.BoostLocation
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface BoostLocationDataSource {
    @ExperimentalCoroutinesApi
    suspend fun createBoostLocation(boostLocation: BoostLocation)
}

/*The Data source implementation will provide logic to decide if the data
* transaction should be remote or local cache.
* i.e. Here we assume everything is local cache
* */
class BoostLocationDataSourceImpl @Inject constructor(
    private val db: BFDatabase
) : BoostLocationDataSource {

    @ExperimentalCoroutinesApi
    override suspend fun createBoostLocation(boostLocation: BoostLocation) {
        db.boostLocationDao().insertBoostLocation(boostLocation)
    }

}