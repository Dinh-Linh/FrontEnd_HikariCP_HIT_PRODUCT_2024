package com.example.hit_product.ui.fragment.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.databinding.FragmentGeneralNotificationBinding
import com.example.hit_product.ui.adapter.GeneralNotificationAdapter
import com.example.hit_product.ui.view_model.NotificationViewModel
import com.example.hit_product.utils.extension.getToken

class GeneralNotificationFragment : BaseFragment<FragmentGeneralNotificationBinding>(FragmentGeneralNotificationBinding::inflate){
    override val viewModel: NotificationViewModel
        get() = ViewModelProvider(this)[NotificationViewModel::class.java]

    private val adapter = GeneralNotificationAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        bindData()
    }
    override fun initData() {
        requireActivity().getToken().let { viewModel.getGeneralNotification(it!!)}
    }

    override fun bindData() {
        binding.rclNotification.adapter = adapter
        binding.rclNotification.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false )
    }

    override fun observeData() {
        viewModel.listGeneralGeneralNotification.observe(viewLifecycleOwner, Observer { generalNotification ->
            if(generalNotification == null){
                Log.d("General notification: ", "is null")
            }else{
                adapter.setDataList(generalNotification.toMutableList())
            }
        })
    }

    override fun setOnClick() {
        binding.btnBackToHome.setOnClickListener {
            findNavController().navigate(R.id.action_generalNotificationFragment_to_introductionFragment)
        }
    }


}