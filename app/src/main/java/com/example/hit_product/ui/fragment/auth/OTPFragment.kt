package com.example.hit_product.ui.fragment.auth

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.data_class.OTPRequest
import com.example.hit_product.databinding.FragmentOtpBinding
import com.example.hit_product.ui.DialogEmailFailure
import com.example.hit_product.ui.DialogEmailOTPFailure
import com.example.hit_product.ui.DialogOTPFailure
import com.example.hit_product.ui.view_model.OTPViewModel


class OTPFragment : BaseFragment<FragmentOtpBinding>(FragmentOtpBinding::inflate) {
    override val viewModel: OTPViewModel
        get() = ViewModelProvider(this)[OTPViewModel::class.java]


    private val otpDialogFailure by lazy { DialogOTPFailure(requireContext()) }
    private val emailDialogFailure by lazy { DialogEmailFailure(requireContext()) }
    private val emailOTPDialogFailure by lazy { DialogEmailOTPFailure(requireContext()) }

    override fun initData() {}

    override fun bindData() {}

    override fun observeData() {
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), "Mã OTP không trùng khớp", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
        val savedEmail = pref.getString("saved_email", "")
    }

    override fun setOnClick() {
        binding.btnConfirmOTP.setOnClickListener {
            val otp = binding.edtOTP.text.toString()
            val email = binding.edtEmail.text.toString()
            val newPassword = binding.edtNewPassword.text.toString()
            val confirmNewPassword = binding.edtConfirmNewPassword.text.toString()
            val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
            val savedEmail = pref.getString("saved_email", "")
            Log.d("OTP Fragment", "saved_email: ${savedEmail}")

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Email chưa được nhập", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (otp.isEmpty()) {
                Toast.makeText(requireContext(), "Mã OTP chưa được nhập", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Không để trống mật khẩu", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (confirmNewPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Chưa xác nhận mật khẩu", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val otpRequest = OTPRequest(email, otp, newPassword)
            if (email == savedEmail) {
                if (newPassword == confirmNewPassword) {
                    viewModel.otp(
                        otpRequest,
                        onOTPSuccess = { apiResponse ->
                            val pref =
                                requireActivity().getSharedPreferences("account", MODE_PRIVATE)
                            pref.edit()
                                .putString("token", "Bearer ${apiResponse.data?.accessToken}")
                                .commit()
                            Handler(Looper.getMainLooper()).postDelayed({
                                findNavController().navigate(R.id.action_OTPFragment_to_loginFragment)
                            }, 2000)
                            context?.let {
                                Toast.makeText(
                                    it,
                                    "Đổi mật khẩu thành công, yêu cầu đăng nhập lại!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )
                } else {
                    context?.let {
                        Toast.makeText(it, "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                context?.let {
                    Toast.makeText(it, "Email không trùng khớp!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        binding.btnBackToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_OTPFragment_to_emailFragment)
        }
    }
}
