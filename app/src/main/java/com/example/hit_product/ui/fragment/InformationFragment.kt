package com.example.hit_product.ui.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.databinding.FragmentForgetBinding
import com.example.hit_product.databinding.FragmentInformationBinding

class InformationFragment : BaseFragment<FragmentInformationBinding>(FragmentInformationBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    override fun initData() {}

    override fun bindData() {}

    override fun observeData() {}

    override fun setOnClick() {

    }

}