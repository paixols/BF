package com.bf.bfmap.confirmation

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
import com.bf.bfmap.databinding.FragmentConfirmationBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ConfirmationFragment : Fragment() {

    /*UI*/
    private lateinit var binding: FragmentConfirmationBinding

    /*VM*/
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by viewModels<ConfirmationViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as BFApplication)
            .appComponent
            .confirmationComponent()
            .create()
            .inject(fragment = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_confirmation, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel
        setOrderConfirmationInfo()
        setCancelRequestAction()
    }

    private fun setOrderConfirmationInfo() {
        viewModel.order.observe(this.viewLifecycleOwner, {
            binding.tvConfirmationInfo.text = viewModel.getOrderAsString(it)
        })
    }

    private fun setCancelRequestAction() {
        binding.btnCancel.setOnClickListener {
            viewModel.cancelOrder()
            findNavController().navigate(R.id.action_ThirdFragment_to_FirstFragment)
        }
    }
}
