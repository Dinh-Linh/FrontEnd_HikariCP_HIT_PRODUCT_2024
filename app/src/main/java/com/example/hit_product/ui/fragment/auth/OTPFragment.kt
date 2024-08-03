package com.example.hit_product.ui.fragment.auth

import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.data_class.OTPRequest
import com.example.hit_product.databinding.FragmentOtpBinding
import com.example.hit_product.ui.DialogEmailFailure
import com.example.hit_product.ui.DialogOTPFailure
import com.example.hit_product.ui.view_model.OTPViewModel


class OTPFragment : BaseFragment<FragmentOtpBinding>(FragmentOtpBinding::inflate) {
    override val viewModel: OTPViewModel
        get() = ViewModelProvider(this)[OTPViewModel::class.java]


    private val otpDialogFailure by lazy { DialogOTPFailure(requireContext())}
    private val emailDialogFailure by lazy { DialogEmailFailure(requireContext()) }

    override fun initData() {}

    override fun bindData() {}

    override fun observeData() {
        viewModel.error.observe(viewLifecycleOwner){
            if(it != null){
                otpDialogFailure.show()
            }
        }
    }

    override fun setOnClick() {
        binding.btnConfirmOTP.setOnClickListener {
            val otp = binding.edtOTP.text.toString()
            val email = binding.edtEmail.text.toString()
            val newPassword = binding.edtNewPassword.text.toString()
            val confirmNewPassword = binding.edtConfirmNewPassword.text.toString()
            val otpRequest = OTPRequest(email, otp, newPassword)
            if (newPassword == confirmNewPassword){
                viewModel.otp(
                    otpRequest,
                    onOTPSuccess = { apiResponse ->
                        val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
                        pref.edit().putString("token", "Bearer ${apiResponse.data?.accessToken}").commit()
                        findNavController().navigate(R.id.action_OTPFragment_to_loginFragment)
                        context?.let {
                            Toast.makeText(it, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }else{
                context?.let {
                    Toast.makeText(it, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.btnBackToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_OTPFragment_to_emailFragment)
        }
    }
}
