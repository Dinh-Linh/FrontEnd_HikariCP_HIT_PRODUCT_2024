package com.example.hit_product.ui.fragment.auth

import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.data_class.LoginRequest
import com.example.hit_product.databinding.FragmentLoginBinding
import com.example.hit_product.ui.CustomViewToast
import com.example.hit_product.ui.DialogLoginFailure
import com.example.hit_product.ui.view_model.LoginViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this)[LoginViewModel::class.java]

    private val loginDialogFailure by lazy { DialogLoginFailure(requireContext()) }
    private val dialog by lazy { Dialog(requireContext()) }
    private val toast by lazy { CustomViewToast(requireContext()) }


    override fun initData() {
        val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
        if (pref.getBoolean("saved_password", false)) {
            val savedUsername = pref.getString("saved_username", "")
            val savedPassword = pref.getString("saved_password", "")
            binding.edtUsername.setText(savedUsername)
            binding.edtPassword.setText(savedPassword)
        }
    }

    override fun bindData() {

    }

    override fun observeData() {
        viewModel.error.observe(viewLifecycleOwner){
            if (it != null){
                loginDialogFailure.show()
            }
        }
    }

    override fun setOnClick() {

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            val loginRequest = LoginRequest(username, password)
            viewModel.login(
                loginRequest,
                onLoginSuccess = { apiResponse ->
                    val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
                    pref.edit().putString("token", "Bearer ${apiResponse.data?.accessToken}").commit()
                    if (pref.getBoolean("saved_password", false)) {
                        pref.edit().putString("saved_username", username)
                        pref.edit().putString("saved_password", password)
                    }
                    pref.edit().apply()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                },
            )
        }

        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_emailFragment)
        }
    }
}