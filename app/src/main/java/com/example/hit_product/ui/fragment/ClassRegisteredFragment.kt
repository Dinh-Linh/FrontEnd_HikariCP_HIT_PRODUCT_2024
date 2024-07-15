package com.example.hit_product.ui.fragment

import androidx.lifecycle.ViewModelProvider
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.databinding.FragmentClassRegisteredBinding

class ClassRegisteredFragment :
    BaseFragment<FragmentClassRegisteredBinding>(FragmentClassRegisteredBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    override fun initData() {

    }

    override fun bindData() {

    }

    override fun observeData() {

    }

    override fun setOnClick() {

    }
}