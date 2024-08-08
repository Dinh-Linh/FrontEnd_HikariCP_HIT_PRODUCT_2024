package com.example.hit_product.ui.fragment.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.databinding.FragmentAccountBinding
import com.example.hit_product.ui.view_model.UserInformationViewModel
import com.example.hit_product.utils.extension.getToken

class AccountFragment : BaseFragment<FragmentAccountBinding>(FragmentAccountBinding::inflate) {
    override val viewModel: UserInformationViewModel
        get() = ViewModelProvider(requireActivity())[UserInformationViewModel::class.java]

    override fun initData() {
        requireActivity().getToken()?.let{ viewModel.getUserInformation(it)}
    }

    override fun bindData() {

    }

    override fun observeData() {
        viewModel.userInformation.observe(viewLifecycleOwner, Observer{ inf ->
            inf?.let{
                binding.userName.text = "${it.username}"
            }
        })
    }

    override fun setOnClick() {
        binding.btnBackToSetting.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_settingFragment)
        }
    }

}