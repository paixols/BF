package com.bf.bfmap.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bf.bfmap.data.models.OrderModel
import com.bf.bfmap.domain.entities.PaymentMethod
import com.bf.bfmap.domain.useCases.BoostOrderUseCase
import com.bf.bfmap.order.utils.DeliveryWindow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrderViewModel @Inject constructor(
    private val boostOrderUseCase: BoostOrderUseCase
) : ViewModel() {

    private val _order: MutableLiveData<OrderModel> by lazy {
        MutableLiveData<OrderModel>().also {
            it.value = OrderModel().apply {
                deliveryTime = DeliveryWindow.MORNING.ordinal
                paymentMethod = PaymentMethod().apply {
                    cardNumber = "1244 4567 3456 1244"
                    expirationDate = "11/26"
                    processor = "Visa"
                }
            }
        }
    }

    val order: LiveData<OrderModel> = _order

    fun setDeliveryTime(deliveryWindow: DeliveryWindow) {
        _order.value?.deliveryTime = deliveryWindow.ordinal
    }

    @ExperimentalCoroutinesApi
    fun requestOrder() {
        viewModelScope.launch {
            order.value?.let {
                boostOrderUseCase.invoke(orderModel = it)
            }
        }
    }

}