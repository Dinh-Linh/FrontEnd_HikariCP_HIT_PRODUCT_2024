package com.example.hit_product.ui.fragment

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.databinding.FragmentForgetBinding
import com.example.hit_product.ui.DialogLoginFailure

class ForgetFragment : BaseFragment<FragmentForgetBinding>(FragmentForgetBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    private val loginDialogFailure by lazy { DialogLoginFailure(requireContext()) }

    override fun initData() {

    }

    override fun bindData() {

    }

    override fun observeData() {

    }

    override fun setOnClick() {
        binding.btnConfirmEmail.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            if(email.isNotEmpty()){

            }
        }
        binding.btnBackToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_forgetFragment_to_loginFragment)
        }
    }
}
