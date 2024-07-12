package com.example.hit_product.ui.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.databinding.FragmentForgetBinding
import com.example.hit_product.databinding.FragmentNewPasswordBinding


class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding>(FragmentNewPasswordBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    override fun initData() {}

    override fun bindData() {}

    override fun observeData() {}

    override fun setOnClick() {
        binding.btnConfirmNewPassword.setOnClickListener {
            val userNewPassword = binding.edtNewPassword.text.toString()
            val userConfirmNewPassword = binding.edtConfirmNewPassword.text.toString()

            when {
                userNewPassword.isEmpty() or userConfirmNewPassword.isEmpty() -> {
                    Toast.makeText(requireContext(), "Vui lòng không để trống", Toast.LENGTH_SHORT).show()
                }

                else -> {

                }
            }
        }

        binding.btnBackToLogin.setOnClickListener{
            findNavController().navigate(R.id.action_newPasswordFragment_to_loginFragment)
        }
        binding.btnConfirmNewPassword.setOnClickListener{
            findNavController().navigate(R.id.action_newPasswordFragment_to_loginFragment)
        }
    }


}
