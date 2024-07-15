package com.example.hit_product.ui.fragment

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.databinding.FragmentClassRegistrationBinding


class ClassRegistrationFragment :
    BaseFragment<FragmentClassRegistrationBinding>(FragmentClassRegistrationBinding::inflate) {
    private val fmClassRegistered = ClassRegisteredFragment()
    private val fmListClass = ListClassFragment()
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    override fun initData() {

    }

    override fun bindData() {

    }

    override fun observeData() {

    }

    @SuppressLint("CommitTransaction")
    override fun setOnClick() {
        binding.listClass.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.fmClassRegistration, fmListClass)
                .addToBackStack(null).commit()
        }
        binding.classRegistered.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fmClassRegistration, fmClassRegistered).addToBackStack(null).commit()
        }
    }
}