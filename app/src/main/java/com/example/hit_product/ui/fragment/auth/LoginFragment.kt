package com.example.hit_product.ui.fragment.auth

import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.data_class.LoginRequest
import com.example.hit_product.databinding.FragmentLoginBinding
import com.example.hit_product.ui.CustomViewToast
import com.example.hit_product.ui.DialogLoginFailure
import com.example.hit_product.ui.view_model.LoginViewModel
import com.example.hit_product.utils.extension.showLoading

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this)[LoginViewModel::class.java]
    private val loginDialogFailure by lazy { DialogLoginFailure(requireContext()) }
    private val dialog by lazy { Dialog(requireContext()) }
    private val toast by lazy { CustomViewToast(requireContext()) }

    override fun initData() {

    }

    override fun bindData() {

    }

    override fun observeData() {
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                loginDialogFailure.show()
                dialog.dismiss()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
        if (pref.getBoolean("save_password", false)) {
            val savedUsername = pref.getString("saved_username", "")
            val savedPassword = pref.getString("saved_password", "")
            if (!savedUsername.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {
                binding.edtUsername.setText(savedUsername)
                binding.edtPassword.setText(savedPassword)
            }
        }
    }

    override fun setOnClick() {
        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            val loginRequest = LoginRequest(username, password)
            if (username.isNotEmpty() && password.isNotEmpty()) {
                dialog.showLoading()
                binding.btnLogin.postDelayed({
                    viewModel.login(
                        loginRequest,
                        onLoginSuccess = { apiResponse ->
                            val pref =
                                requireActivity().getSharedPreferences("account", MODE_PRIVATE)
                            val editor = pref.edit()
                            editor.putString("token", "Bearer ${apiResponse.data?.accessToken}")
                            if (pref.getBoolean("save_password", false)) {
                                editor.putString("saved_username", username)
                                editor.putString("saved_password", password)
                            }
                            editor.commit()
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                            dialog.dismiss()
                        },
                    )
                }, 1500)

            } else {
                toast.makeText(
                    requireContext(),
                    "Vui lòng nhập tên đăng nhập và mật khẩu",
                    CustomViewToast.SHORT,
                    R.drawable.warning_icon_toast
                ).show()
            }

        }

        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_emailFragment)
        }
    }

}
