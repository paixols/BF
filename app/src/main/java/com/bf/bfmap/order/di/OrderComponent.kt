package com.bf.bfmap.order.di

import com.bf.bfmap.order.OrderFragment
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Subcomponent(
    modules = [
        OrderModule::class
    ]
)
interface OrderComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): OrderComponent
    }

    @ExperimentalCoroutinesApi
    fun inject(fragment: OrderFragment)
}