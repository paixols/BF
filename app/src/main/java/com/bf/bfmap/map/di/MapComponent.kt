package com.bf.bfmap.map.di

import com.bf.bfmap.map.MapFragment
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Subcomponent(
    modules = [
        MapModule::class
    ]
)
interface MapComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MapComponent
    }

    @ExperimentalCoroutinesApi
    fun inject(fragment: MapFragment)
}