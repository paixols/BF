package com.bf.bfmap.domain.useCases

import com.bf.bfmap.data.models.OrderModel
import com.bf.bfmap.domain.repos.BoostOrderRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/*
* Use case can be used to filter or modify and provide a unique data set
* */
class BoostConfirmationUseCase @Inject constructor(
    private val boostOrderRepository: BoostOrderRepository
) {

    @ExperimentalCoroutinesApi
    suspend fun invoke(): OrderModel =
        boostOrderRepository.getOrder()

    @ExperimentalCoroutinesApi
    suspend fun cancelOrder() {
        boostOrderRepository.nukeTable()
    }

}