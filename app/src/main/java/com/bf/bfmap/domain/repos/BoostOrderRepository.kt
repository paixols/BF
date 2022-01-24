package com.bf.bfmap.domain.repos

import com.bf.bfmap.data.models.OrderModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface BoostOrderRepository {
    @ExperimentalCoroutinesApi
    suspend fun saveOrder(orderModel: OrderModel)

    @ExperimentalCoroutinesApi
    suspend fun getOrder(): OrderModel

    @ExperimentalCoroutinesApi
    suspend fun nukeTable()
}