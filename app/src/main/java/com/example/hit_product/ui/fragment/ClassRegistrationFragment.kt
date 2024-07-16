package com.example.hit_product.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.databinding.FragmentClassRegistrationBinding


class ClassRegistrationFragment :
    BaseFragment<FragmentClassRegistrationBinding>(FragmentClassRegistrationBinding::inflate) {
    private val fmClassRegistered = CourseRegisteredFragment()
    private val fmListClass = ListCourseFragment()
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
        binding.tvListClass.setTextColor(Color.rgb(240, 108, 37))
        binding.viewListClass.setBackgroundColor(Color.rgb(240, 108, 37))
        childFragmentManager.beginTransaction().replace(R.id.fmClassRegistration, fmListClass)
            .addToBackStack(null).commit()

        binding.listClass.setOnClickListener {
            childFragmentManager.popBackStack()
            childFragmentManager.beginTransaction().replace(R.id.fmClassRegistration, fmListClass)
                .addToBackStack(null).commit()

            binding.tvListClass.setTextColor(Color.rgb(240, 108, 37))
            binding.viewListClass.setBackgroundColor(Color.rgb(240, 108, 37))
            binding.tvClassRegistered.setTextColor(Color.rgb(87, 92, 107))
            binding.viewClassRegistered.setBackgroundColor(Color.TRANSPARENT)
        }

        binding.classRegistered.setOnClickListener {
            childFragmentManager.popBackStack()
            childFragmentManager.beginTransaction()
                .replace(R.id.fmClassRegistration, fmClassRegistered).addToBackStack(null).commit()

            binding.tvListClass.setTextColor(Color.rgb(87, 92, 107))
            binding.viewListClass.setBackgroundColor(Color.TRANSPARENT)
            binding.tvClassRegistered.setTextColor(Color.rgb(240, 108, 37))
            binding.viewClassRegistered.setBackgroundColor(Color.rgb(240, 108, 37))
        }
    }
}