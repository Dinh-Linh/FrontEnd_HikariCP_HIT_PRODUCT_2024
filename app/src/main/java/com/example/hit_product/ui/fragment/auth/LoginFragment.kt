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
        viewModel.error.observe(viewLifecycleOwner){
            if (it != null){
                loginDialogFailure.show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
        if (pref.getBoolean("save_password", false)) {
            val savedUsername = pref.getString("saved_username", "")
            val savedPassword = pref.getString("saved_password", "")
            Log.d("LoginFragment", "Saved username: $savedUsername")
            Log.d("LoginFragment", "Saved password: $savedPassword")
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
            viewModel.login(
                loginRequest,
                onLoginSuccess = { apiResponse ->
                    val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
                    val editor = pref.edit()
                    editor.putString("token", "Bearer ${apiResponse.data?.accessToken}")
                    if (pref.getBoolean("save_password", false)) {
                        editor.putString("saved_username", username)
                        editor.putString("saved_password", password)
                    }
                    editor.commit()
                    Log.d("LoginFragment", "Saving username: $username")
                    Log.d("LoginFragment", "Saving password: $password")
                    Log.d("LoginFragment", "Save password switch is checked: ${pref.getBoolean("save_password", false)}")

                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                },
            )
        }

        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_emailFragment)
        }
    }

}
