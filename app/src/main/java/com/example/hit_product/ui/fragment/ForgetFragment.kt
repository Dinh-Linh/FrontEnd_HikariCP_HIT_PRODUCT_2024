package com.example.hit_product.ui.fragment

import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.source.Members
import com.example.hit_product.databinding.FragmentForgetBinding

class ForgetFragment : BaseFragment<FragmentForgetBinding>(FragmentForgetBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    override fun initData() {}

    override fun bindData() {}

    override fun observeData() {}

    override fun setOnClick() {
        binding.btnConfirmEmail.setOnClickListener {
            val userEmail = binding.edtEmail.text.toString()
            when {
                userEmail.isEmpty() -> {
                    Toast.makeText(requireContext(), "Vui lòng nhập email", Toast.LENGTH_SHORT).show()
                }

                else -> {

                }
            }
        }

        binding.btnBackToLogin.setOnClickListener{
            findNavController().navigate(R.id.action_forgetFragment_to_OTPFragment)
        }
    }


}
