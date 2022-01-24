package com.bf.bfmap.domain.useCases

import android.util.Log
import com.bf.bfmap.data.models.OrderModel
import com.bf.bfmap.domain.repos.BoostOrderRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/*
* Use case can be used to filter or modify and provide a unique data set
* i.e. Filter orders that are active, or past orders.
* */
class BoostOrderUseCase @Inject constructor(
    private val boostOrderRepository: BoostOrderRepository
) {

    @ExperimentalCoroutinesApi
    suspend fun invoke(orderModel: OrderModel) {
        if (isActiveOrder()) {
            boostOrderRepository.saveOrder(orderModel)
        } else {
            Log.d(TAG, "Past Orders")
        }
    }

    /*We assume all orders are active*/
    private fun isActiveOrder(): Boolean = true

    private companion object {
        val TAG = this::class.simpleName
    }

}