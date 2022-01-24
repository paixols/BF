package com.bf.bfmap.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bf.bfmap.R
import com.bf.bfmap.app.BFApplication
import com.bf.bfmap.databinding.FragmentOrderBinding
import com.bf.bfmap.domain.entities.PaymentMethod
import com.bf.bfmap.order.utils.DeliveryWindow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class OrderFragment : Fragment() {

    /*UI*/
    private lateinit var binding: FragmentOrderBinding

    /*VM*/
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by viewModels<OrderViewModel> { viewModelFactory }


    /*LifeCycle*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as BFApplication)
            .appComponent
            .orderComponent()
            .create()
            .inject(fragment = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_order, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel
        setupOrderDefaultInformation()
        setupDeliveryWindowActions()
        setupOrderRequestAction()
    }

    private fun setupOrderDefaultInformation() {
        viewModel.order.observe(this.viewLifecycleOwner, { defaultOrderInfo ->
            setDefaultDeliveryWindow(defaultOrderInfo.deliveryTime)
            setDefaultPaymentMethod(defaultOrderInfo.paymentMethod)
        })
    }

    private fun setDefaultPaymentMethod(paymentMethod: PaymentMethod?) {
        paymentMethod?.let {
            binding.rbPaymentMethod.text =
                resources.getString(
                    R.string.payment_method,
                    it.cardNumber,
                    it.expirationDate,
                    it.cardNumber
                )
        }
    }

    private fun setDefaultDeliveryWindow(deliveryTime: Int?) {
        deliveryTime?.let {
            when (deliveryTime) {
                DeliveryWindow.MORNING.ordinal -> {
                    binding.rbTimeAfternoon.isChecked = false
                    binding.rbTimeMorning.isChecked = true
                }
                DeliveryWindow.AFTERNOON.ordinal -> {
                    binding.rbTimeAfternoon.isChecked = true
                    binding.rbTimeMorning.isChecked = false
                }
            }
        }
    }

    private fun setupDeliveryWindowActions() {
        binding.rgTimeSelection.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_time_morning -> {
                    viewModel.setDeliveryTime(DeliveryWindow.MORNING)
                }
                R.id.rb_time_afternoon -> {
                    viewModel.setDeliveryTime(DeliveryWindow.AFTERNOON)
                }
            }
        }
    }

    private fun setupOrderRequestAction() {
        binding.btnOrder.setOnClickListener {
            viewModel.requestOrder()
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
        }
    }

}
