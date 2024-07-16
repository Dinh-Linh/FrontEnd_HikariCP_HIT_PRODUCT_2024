package com.example.hit_product.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

    private val fakeDataListClass = mutableListOf(
        CourseRegistration("1", "Android Class", null, "Nguyen Duy Minh Quan", null, null, null),
        CourseRegistration("2", "ReactJS Class", null, "Nguyen Thi Trang", null, null, null),
        CourseRegistration("3", "Java SpringBoot Class", null, "Nguyen Tien Kien", null, null, null)
    )

    private val adapter = ListCourseAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        bindData()
    }

    override fun initData() {
        //adapter.setDataList(fakeDataListClass)
        requireActivity().getToken().let { viewModel.getAllCourse(it!!) }
    }

    override fun bindData() {
        binding.rclListClass.adapter = adapter
        binding.rclListClass.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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