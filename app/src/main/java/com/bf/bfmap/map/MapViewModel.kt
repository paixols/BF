package com.bf.bfmap.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bf.bfmap.domain.useCases.BoostLocationUseCase
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject


class MapViewModel @Inject constructor(
    private val boostLocationUseCase: BoostLocationUseCase
) : ViewModel() {

    private val _mapLocation: MutableLiveData<Pair<LatLng, Float>> by lazy {
        MutableLiveData<Pair<LatLng, Float>>().apply {
            //Initial Location in Tampa,FL with a centered parking lot
            value = Pair(LatLng(27.8920506,-82.5164229), 17f)
        }
    }

    private val _mapType: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            value = GoogleMap.MAP_TYPE_SATELLITE
        }
    }

    val mapLocation: LiveData<Pair<LatLng, Float>> = _mapLocation
    val mapType: LiveData<Int> = _mapType

    @ExperimentalCoroutinesApi
    fun saveBoostLocation() {
        viewModelScope.launch {
            mapLocation.value?.let {
                boostLocationUseCase.invoke(it.first)
            }
        }
    }

    fun saveMapPosition(latLng: LatLng, zoom: Float) {
        _mapLocation.postValue(
            Pair(latLng, zoom)
        )
    }

    fun saveMapType(mapType: Int) {
        _mapType.postValue(mapType)
    }
}
