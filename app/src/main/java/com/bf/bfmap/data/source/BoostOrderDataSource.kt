package com.bf.bfmap.data.source

import com.bf.bfmap.db.BFDatabase
import com.bf.bfmap.domain.entities.BoostOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

interface BoostOrderDataSource {
    @ExperimentalCoroutinesApi
    suspend fun saveOrder(boostOrder: BoostOrder)

    @ExperimentalCoroutinesApi
    suspend fun getOrder(): BoostOrder

    @ExperimentalCoroutinesApi
    suspend fun nukeTable()
}

/* The Data source implementation will provide logic to decide if the data
*  transaction should be remote or local cache.
*  i.e. Here we assume everything is local cache
* */
class BoostOrderDataSourceImpl @Inject constructor(
    private val db: BFDatabase
) : BoostOrderDataSource {

    @ExperimentalCoroutinesApi
    override suspend fun saveOrder(boostOrder: BoostOrder) {
        db.boostOrderDao().insertBoostOrder(boostOrder)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getOrder(): BoostOrder =
        db.boostOrderDao().getOrder()

    @ExperimentalCoroutinesApi
    override suspend fun nukeTable() {
        db.boostOrderDao().nukeTable()
    }

}
