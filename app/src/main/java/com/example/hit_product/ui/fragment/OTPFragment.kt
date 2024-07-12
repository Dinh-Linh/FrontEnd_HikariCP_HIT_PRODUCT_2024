package com.example.hit_product.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.databinding.FragmentForgetBinding
import com.example.hit_product.databinding.FragmentOTPBinding


class OTPFragment : BaseFragment<FragmentOTPBinding>(FragmentOTPBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    override fun initData() {}

    override fun bindData() {}

    override fun observeData() {}

    override fun setOnClick() {
        binding.btnConfirmOTP.setOnClickListener {
            val userOTP = binding.edtOTP.text.toString()

            when {
                userOTP.isEmpty() -> {
                    Toast.makeText(requireContext(), "Vui lòng nhập OTP", Toast.LENGTH_SHORT).show()
                }

                else -> {

                }
            }
        }

        binding.btnBackToLogin.setOnClickListener{
            findNavController().navigate(R.id.action_OTPFragment_to_loginFragment)
        }
        binding.btnConfirmOTP.setOnClickListener{
            findNavController().navigate(R.id.action_OTPFragment_to_newPasswordFragment)
        }
    }


}
