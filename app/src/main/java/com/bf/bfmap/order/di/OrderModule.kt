package com.bf.bfmap.order.di

import androidx.lifecycle.ViewModel
import com.bf.bfmap.data.repos.BoostOrderRepositoryImpl
import com.bf.bfmap.data.source.BoostOrderDataSource
import com.bf.bfmap.data.source.BoostOrderDataSourceImpl
import com.bf.bfmap.di.vm.ViewModelKey
import com.bf.bfmap.domain.repos.BoostOrderRepository
import com.bf.bfmap.order.OrderViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class OrderModule {

    @Binds
    abstract fun provideBoostOrderDataSource(boostOrderDataSourceImpl: BoostOrderDataSourceImpl): BoostOrderDataSource

    @Binds
    abstract fun providesBoostOrderRepository(boostOrderRepositoryImpl: BoostOrderRepositoryImpl): BoostOrderRepository

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    abstract fun bindViewModel(viewModel: OrderViewModel): ViewModel
}