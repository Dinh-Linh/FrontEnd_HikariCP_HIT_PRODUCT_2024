package com.example.hit_product.ui.fragment.auth

import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.data_class.EmailRequest
import com.example.hit_product.databinding.FragmentForgetEmailBinding
import com.example.hit_product.ui.DialogEmailFailure
import com.example.hit_product.ui.view_model.EmailViewModel

class EmailFragment : BaseFragment<FragmentForgetEmailBinding>(FragmentForgetEmailBinding::inflate) {
    override val viewModel: EmailViewModel
        get() = ViewModelProvider(this)[EmailViewModel::class.java]

    private val emailDialogFailure by lazy { DialogEmailFailure(requireContext()) }

    override fun initData() {

    }

    override fun bindData() {

    }

    override fun observeData() {
        viewModel.error.observe(viewLifecycleOwner){
            if(it != null){
                emailDialogFailure.show()
            }
        }
    }

    override fun setOnClick() {
        binding.btnConfirmEmail.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val emailRequest = EmailRequest(email)
            viewModel.email(
                emailRequest,
                onEmailSuccess = { apiResponse ->
                    val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
                    pref.edit().putString("token", "Bearer ${apiResponse.data?.accessToken}").commit()
                    findNavController().navigate(R.id.action_emailFragment_to_OTPFragment)
                    context?.let {
                        Toast.makeText(it, "Đã gửi OTP", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }

        binding.btnBackToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_emailFragment_to_loginFragment)
        }
    }
}
