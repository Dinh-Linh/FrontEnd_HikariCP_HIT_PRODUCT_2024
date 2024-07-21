package com.example.hit_product.ui.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.databinding.FragmentNewPasswordBinding
import com.example.hit_product.ui.CustomViewToast
import com.example.hit_product.ui.DialogEmailFailure


class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding>(FragmentNewPasswordBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]
    private val confirmPasswordDialogFailure by lazy{ DialogEmailFailure(requireContext())}
    private val dialog by lazy { Dialog(requireContext()) }
    private val toast by lazy { CustomViewToast(requireContext())}

    override fun initData() {

    }

    override fun bindData() {

    }

    override fun observeData() {

    }

    override fun setOnClick() {
        binding.btnConfirmPassword.setOnClickListener{
        }
    }
}