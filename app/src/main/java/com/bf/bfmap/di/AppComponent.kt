package com.bf.bfmap.di

import android.content.Context
import com.bf.bfmap.confirmation.di.ConfirmationComponent
import com.bf.bfmap.di.vm.ViewModelBuilderModule
import com.bf.bfmap.map.di.MapComponent
import com.bf.bfmap.order.di.OrderComponent
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelBuilderModule::class,
        SubComponentsModule::class
    ]
)
@ExperimentalCoroutinesApi
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    /*Components*/
    fun mapComponent(): MapComponent.Factory

    fun orderComponent(): OrderComponent.Factory

    @ExperimentalCoroutinesApi
    fun confirmationComponent(): ConfirmationComponent.Factory
}