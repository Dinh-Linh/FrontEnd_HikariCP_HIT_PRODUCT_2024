package com.example.hit_product.ui

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.source.Account
import com.example.hit_product.databinding.FragmentForgetBinding

class ForgetFragment : BaseFragment<FragmentForgetBinding>(FragmentForgetBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    private val listAccount = mutableListOf(
        Account("001", "user1", "password1", "2004", "2010", "2010"),
        Account("002", "user2", "password2", "2004", "2010", "2010"),
        Account("003", "user3", "password3", "2004", "2010", "2010"),
        Account("004", "user4", "password4", "2004", "2010", "2010"),
        Account("005", "user5", "password5", "2004", "2010", "2010")
    )

    override fun initData() {}

    override fun bindData() {}

    override fun observeData() {}

    override fun setOnClick() {
        binding.btnConfirmEmail.setOnClickListener {
            val userEmail = binding.edtEmail.text.toString()


            when {
                userEmail.isEmpty() -> {
                    Toast.makeText(requireContext(), "Vui lòng nhập email", Toast.LENGTH_SHORT).show()
                }

                else -> {

                }
            }
        }

        binding.btnBackToLogin.setOnClickListener{
            findNavController().navigate(R.id.action_forgetFragment_to_loginFragment)
        }
    }


}
