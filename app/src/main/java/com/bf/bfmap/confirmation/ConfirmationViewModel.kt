package com.bf.bfmap.confirmation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bf.bfmap.data.models.OrderModel
import com.bf.bfmap.domain.useCases.BoostConfirmationUseCase
import com.bf.bfmap.order.utils.DeliveryWindow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ConfirmationViewModel @Inject constructor(
    private val confirmationUseCase: BoostConfirmationUseCase
) : ViewModel() {

    private val _order: MutableLiveData<OrderModel> by lazy {
        MutableLiveData<OrderModel>()
    }

    val order: LiveData<OrderModel> = _order

    init {
        getCurrentOrder()
    }

    @ExperimentalCoroutinesApi
    private fun getCurrentOrder() {
        viewModelScope.launch {
            confirmationUseCase.invoke().let {
                _order.postValue(it)
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun cancelOrder() {
        viewModelScope.launch {
            confirmationUseCase.cancelOrder()
        }
    }

    fun getOrderAsString(orderModel: OrderModel): String {
        val result = StringBuilder()
        orderModel.let {
            result.append(" Delivery Time: \n\n ${getDeliveryWindow(orderModel.deliveryTime)} \n\n")
            result.append(
                " Payment Information: \n\n " +
                        "Card Number: ${orderModel.paymentMethod?.cardNumber} \n " +
                        "Expiration Date: ${orderModel.paymentMethod?.expirationDate} \n " +
                        "Processor: ${orderModel.paymentMethod?.processor}"
            )

        }
        return result.toString()
    }

    private fun getDeliveryWindow(deliveryWindow: Int?): String {
        return when (deliveryWindow) {
            0 -> DeliveryWindow.MORNING.name
            1 -> DeliveryWindow.AFTERNOON.name
            else -> DeliveryWindow.AFTERNOON.name
        }
    }

}