package com.bf.bfmap.data.repos

import com.bf.bfmap.data.models.OrderModel
import com.bf.bfmap.data.source.BoostOrderDataSource
import com.bf.bfmap.domain.entities.BoostOrder
import com.bf.bfmap.domain.repos.BoostOrderRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/*Repository Implementation makes sure we exchange data
* in a valid format before we push/pull from the data source domain
* */
class BoostOrderRepositoryImpl @Inject constructor(
    private val orderDataSource: BoostOrderDataSource
) : BoostOrderRepository {

    @ExperimentalCoroutinesApi
    override suspend fun saveOrder(orderModel: OrderModel) {
        orderDataSource.saveOrder(mapToBoostOrder(orderModel))
    }

    @ExperimentalCoroutinesApi
    override suspend fun getOrder(): OrderModel =
        mapFromBoostOrder(orderDataSource.getOrder())

    @ExperimentalCoroutinesApi
    override suspend fun nukeTable() {
        orderDataSource.nukeTable()
    }

    private fun mapFromBoostOrder(boostOrder: BoostOrder): OrderModel =
        OrderModel().also {
            it.deliveryTime = boostOrder.deliveryTime
            it.paymentMethod = boostOrder.paymentMethod
        }

    private fun mapToBoostOrder(orderModel: OrderModel): BoostOrder =
        BoostOrder().also {
            it.deliveryTime = orderModel.deliveryTime
            it.paymentMethod = orderModel.paymentMethod
        }
}