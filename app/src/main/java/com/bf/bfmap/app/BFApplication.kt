package com.bf.bfmap.app

import android.app.Application
import android.util.Log
import com.bf.bfmap.di.AppComponent
import com.bf.bfmap.di.DaggerAppComponent
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapsSdkInitializedCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi

class BFApplication :
    Application(),
    OnMapsSdkInitializedCallback {

    override fun onCreate() {
        super.onCreate()

        MapsInitializer.initialize(
            applicationContext,
            MapsInitializer.Renderer.LATEST,
            this
        )
    }

    /*Google Cloud Map*/
    override fun onMapsSdkInitialized(p0: MapsInitializer.Renderer) {
        when (p0) {
            MapsInitializer.Renderer.LATEST -> Log.d(TAG, "BF Map Style rendered")
            MapsInitializer.Renderer.LEGACY -> Log.d(TAG, "Legacy Map Style rendered")
        }
    }

    /*DI*/
    @ExperimentalCoroutinesApi
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    @ExperimentalCoroutinesApi
    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    private companion object {
        private val TAG = this::class.java.simpleName
    }

}