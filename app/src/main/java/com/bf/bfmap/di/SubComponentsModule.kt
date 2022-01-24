package com.bf.bfmap.di

import com.bf.bfmap.confirmation.di.ConfirmationComponent
import com.bf.bfmap.map.di.MapComponent
import com.bf.bfmap.order.di.OrderComponent
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module(subcomponents = [MapComponent::class, OrderComponent::class, ConfirmationComponent::class])
@ExperimentalCoroutinesApi
object SubComponentsModule