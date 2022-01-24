package com.bf.bfmap.di.vm

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelBuilderModule {

    @Binds
    abstract fun bindViewModelFactory(
        factory: BFViewModelFactory
    ): ViewModelProvider.Factory
}