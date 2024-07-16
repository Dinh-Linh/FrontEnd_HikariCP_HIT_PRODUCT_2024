package com.example.hit_product.ui.fragment

import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.CourseRegistration
import com.example.hit_product.databinding.FragmentClassDetailsBinding
import com.example.hit_product.ui.view_model.ListCourseViewModel
import com.example.hit_product.utils.extension.getToken

class ClassDetailsFragment :
    BaseFragment<FragmentClassDetailsBinding>(FragmentClassDetailsBinding::inflate) {
    override val viewModel: ListCourseViewModel
        get() = ViewModelProvider(this)[ListCourseViewModel::class.java]

    private var courseRegistration: CourseRegistration? = null

    override fun initData() {
        requireActivity().getToken().let { viewModel.getAllCourse(it!!) }

        courseRegistration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("course", CourseRegistration::class.java)
        } else {
            arguments?.getSerializable("course") as CourseRegistration
        }
    }


    override fun bindData() {
        courseRegistration?.apply {
            binding.className.text = name
            binding.leaderName.text = leader
            binding.introClass.text = detail
            Glide.with(requireContext()).load(picture).into(binding.avtLeader)
        }
        Log.d("Course Regis: ", courseRegistration.toString())
    }

    override fun observeData() {

    }

    override fun setOnClick() {

    }

}