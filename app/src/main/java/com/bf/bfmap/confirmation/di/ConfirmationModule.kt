package com.bf.bfmap.confirmation.di

import androidx.lifecycle.ViewModel
import com.bf.bfmap.confirmation.ConfirmationViewModel
import com.bf.bfmap.data.repos.BoostOrderRepositoryImpl
import com.bf.bfmap.data.source.BoostOrderDataSource
import com.bf.bfmap.data.source.BoostOrderDataSourceImpl
import com.bf.bfmap.di.vm.ViewModelKey
import com.bf.bfmap.domain.repos.BoostOrderRepository
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
abstract class ConfirmationModule {

    @Binds
    abstract fun provideBoostOrderDataSource(boostOrderDataSourceImpl: BoostOrderDataSourceImpl): BoostOrderDataSource

    @Binds
    abstract fun providesBoostOrderRepository(boostOrderRepositoryImpl: BoostOrderRepositoryImpl): BoostOrderRepository

    @Binds
    @IntoMap
    @ViewModelKey(ConfirmationViewModel::class)
    abstract fun bindViewModel(viewModel: ConfirmationViewModel): ViewModel
}