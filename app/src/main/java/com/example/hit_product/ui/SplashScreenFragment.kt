package com.example.hit_product.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.databinding.FragmentSplashScreenBinding



class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    override fun initData() {

    }

    override fun bindData() {

    }

    override fun observeData() {

    }

    override fun setOnClick() {
        binding.splashScreen.alpha = 0.8f
        binding.splashScreen.animate().setDuration(1500).withEndAction {
            requireActivity().runOnUiThread{
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            }
        }.start()
    }

}