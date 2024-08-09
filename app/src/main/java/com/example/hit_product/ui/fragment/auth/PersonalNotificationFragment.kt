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
import com.example.hit_product.databinding.FragmentPersonalNotificationBinding
import com.example.hit_product.ui.adapter.PersonalNotificationAdapter
import com.example.hit_product.ui.adapter.UserInformationAdapter
import com.example.hit_product.ui.view_model.PersonalNotificationViewModel
import com.example.hit_product.ui.view_model.UserInformationViewModel
import com.example.hit_product.utils.extension.getToken

class PersonalNotificationFragment : BaseFragment<FragmentPersonalNotificationBinding>(FragmentPersonalNotificationBinding::inflate) {
    override val viewModel: PersonalNotificationViewModel
        get() = ViewModelProvider(this)[PersonalNotificationViewModel::class.java]

    private val viewModel2 : UserInformationViewModel
        get() = ViewModelProvider(this)[UserInformationViewModel::class.java]

    private val adapter = PersonalNotificationAdapter()
    private val informationAdapter2= UserInformationAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        bindData()
    }

    override fun initData() {
        requireActivity().getToken()?.let{viewModel2.getUserInformation(it)}
    }

    override fun bindData() {
        binding.rclPersonalNotification.adapter = adapter
        binding.rclPersonalNotification.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    override fun observeData() {
        viewModel2.userInformation.observe(viewLifecycleOwner, Observer { informationList ->
            if(informationList == null){
                Log.d("Information List", "is Empty")
            }else{
                val memberId = informationList.id
                Log.d("Information List", "is Ready")
                requireActivity().getToken()?.let { viewModel.getPersonalNotification(memberId, it) }
            }
        })

        viewModel.listPersonalNotification.observe(viewLifecycleOwner, Observer { personalNotification ->
            if(personalNotification == null){
                Log.d("Personal notification: ", "is null")
            }else{
                adapter.setDataList(personalNotification.toMutableList())
            }
        })

    }

    override fun setOnClick() {
        binding.btnBackToHome.setOnClickListener {
            findNavController().navigate(R.id.action_personal_NotificationFragment_to_homeFragment)
        }
    }





}