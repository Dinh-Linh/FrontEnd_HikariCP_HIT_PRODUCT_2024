package com.example.hit_product.ui

import android.app.Dialog
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.source.Account
import com.example.hit_product.databinding.FragmentLoginBinding
import com.example.hit_product.utils.extension.showLoading
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    private val loginDialogFailure by lazy { DialogLoginFailure(requireContext()) }
    private val dialog by lazy { Dialog(requireContext()) }
    private val toast by lazy { CustomViewToast(requireContext()) }

    private val listAccount = mutableListOf(
        Account("001", "user1","password1", "2004", "2010", "2010"),
        Account("002", "user2", "password2", "2004", "2010", "2010"),
        Account("003", "user3", "password3", "2004", "2010", "2010"),
        Account("004", "user4", "password4", "2004", "2010", "2010"),
        Account("005", "user5", "password5", "2004", "2010", "2010"),
    )

    override fun initData() {

    }

    override fun bindData() {

    }

    override fun observeData() {

    }

    override fun setOnClick() {

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                dialog.showLoading()
                binding.btnLogin.postDelayed({
                    val isAccountValid =
                        listAccount.any { it.username == username && it.password == password }
                    if (isAccountValid) {
                        dialog.dismiss()
                        toast.makeText(
                            requireContext(),
                            "Login Successful",
                            CustomViewToast.SHORT,
                            R.drawable.success_icon_toast
                        ).show()
                    } else {
                        dialog.dismiss()
                        loginDialogFailure.show()
                    }
                }, 2000)

            } else {
                toast.makeText(
                    requireContext(),
                    "Vui lòng nhâ tên đăng nhập và mật khẩu",
                    CustomViewToast.SHORT,
                    R.drawable.warning_icon_toast
                ).show()
            }
        }

        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgetFragment)
        }
    }

}