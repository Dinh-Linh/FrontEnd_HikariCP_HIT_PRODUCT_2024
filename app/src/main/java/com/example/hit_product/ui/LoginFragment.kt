package com.example.hit_product.ui

import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.source.Members
import com.example.hit_product.databinding.FragmentLoginBinding
import com.example.hit_product.utils.extension.showLoading

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    private val loginDialogFailure by lazy { DialogLoginFailure(requireContext()) }
    private val dialog by lazy { Dialog(requireContext()) }
    private val toast by lazy { CustomViewToast(requireContext()) }

    private val listMembers = mutableListOf(
        Members("1", null, null, null, null, null, null, null, null, null, null, "user1", "pass1", null, null, null),
        Members("2", null, null, null, null, null, null, null, null, null, null, "user2", "pass2", null, null, null),
        Members("3", null, null, null, null, null, null, null, null, null, null, "user3", "pass3", null, null, null),
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
                        listMembers.any { it.username == username && it.password == password }
                    if (isAccountValid) {
                        dialog.dismiss()
//                        toast.makeText(
//                            requireContext(),
//                            "Login Successful",
//                            CustomViewToast.SHORT,
//                            R.drawable.success_icon_toast
//                        ).show()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
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