package com.bf.bfmap.confirmation.di

import com.bf.bfmap.confirmation.ConfirmationFragment
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Subcomponent(
    modules = [
        ConfirmationModule::class
    ]
)
@ExperimentalCoroutinesApi
interface ConfirmationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ConfirmationComponent
    }

    @ExperimentalCoroutinesApi
    fun inject(fragment: ConfirmationFragment)
}