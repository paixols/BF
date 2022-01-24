package com.bf.bfmap.map.di

import androidx.lifecycle.ViewModel
import com.bf.bfmap.data.repos.BoostLocationLocationRepositoryImpl
import com.bf.bfmap.data.source.BoostLocationDataSource
import com.bf.bfmap.data.source.BoostLocationDataSourceImpl
import com.bf.bfmap.di.vm.ViewModelKey
import com.bf.bfmap.domain.repos.BoostLocationRepository
import com.bf.bfmap.map.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MapModule {

    @Binds
    abstract fun provideBoostLocationDataSource(boostDataSourceImpl: BoostLocationDataSourceImpl): BoostLocationDataSource

    @Binds
    abstract fun providesBoostLocationRepository(boostLocationRepositoryImpl: BoostLocationLocationRepositoryImpl): BoostLocationRepository

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindViewModel(viewModel: MapViewModel): ViewModel
}