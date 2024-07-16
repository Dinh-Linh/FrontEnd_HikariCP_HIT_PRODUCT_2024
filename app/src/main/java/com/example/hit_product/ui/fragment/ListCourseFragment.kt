package com.example.hit_product.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.CourseRegistration
import com.example.hit_product.databinding.FragmentListClassBinding
import com.example.hit_product.ui.adapter.ListCourseAdapter
import com.example.hit_product.ui.view_model.ListCourseViewModel
import com.example.hit_product.utils.extension.getToken


class ListCourseFragment :
    BaseFragment<FragmentListClassBinding>(FragmentListClassBinding::inflate) {
    override val viewModel: ListCourseViewModel
        get() = ViewModelProvider(this)[ListCourseViewModel::class.java]

    private val adapter = ListCourseAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        bindData()
    }

    override fun initData() {
        requireActivity().getToken().let { viewModel.getAllCourse(it!!) }
    }

    override fun bindData() {
        binding.rclListClass.adapter = adapter
        binding.rclListClass.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.onClick = {
            findNavController().navigate(R.id.action_classRegistrationFragment_to_classInformationFragment)
        }

    }

    override fun observeData() {
        viewModel.listCourse.observe(viewLifecycleOwner, Observer { courses ->
            if (courses == null){
                Log.d("List course: ", "is Empty")
            }
            else{
                adapter.setDataList(courses.toMutableList())
            }
        })
    }

    override fun setOnClick() {

    }
}