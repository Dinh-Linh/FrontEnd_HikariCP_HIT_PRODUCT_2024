package com.example.hit_product.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.CourseRegistration
import com.example.hit_product.databinding.FragmentClassInformationBinding
import com.example.hit_product.ui.view_model.ListCourseViewModel
import com.example.hit_product.utils.extension.getToken

class ClassInformationFragment :
    BaseFragment<FragmentClassInformationBinding>(FragmentClassInformationBinding::inflate) {
    override val viewModel: ListCourseViewModel
        get() = ViewModelProvider(this)[ListCourseViewModel::class.java]

    override fun initData() {
        requireActivity().getToken().let { viewModel.getAllCourse(it!!) }
    }

    override fun bindData() {

    }

    override fun observeData() {

    }

    override fun setOnClick() {

    }

}