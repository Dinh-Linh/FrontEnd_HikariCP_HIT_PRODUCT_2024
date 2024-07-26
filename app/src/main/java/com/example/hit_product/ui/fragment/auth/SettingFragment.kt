package com.example.hit_product.ui.fragment.auth

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.databinding.FragmentSettingBinding
import com.example.hit_product.ui.view_model.SettingViewModel
import com.example.hit_product.utils.extension.getToken

class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate){
    override val viewModel : SettingViewModel
        get() = ViewModelProvider(this)[SettingViewModel::class.java]

    override fun initData() {

    }

    override fun bindData() {

    }

    override fun observeData() {

    }

    override fun setOnClick() {
        binding.logOut.setOnClickListener {
            val token = requireActivity().getToken()
            if (token != null){
                viewModel.logOut(token)
                findNavController().navigate(R.id.action_settingFragment_to_loginFragment)
            }
        }
    }
}