package com.example.hit_product.ui.fragment.classroom

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.data_class.CourseRegistration
import com.example.hit_product.databinding.FragmentClassRegisteredBinding
import com.example.hit_product.ui.adapter.ListCourseAdapter

class CourseRegisteredFragment :
    BaseFragment<FragmentClassRegisteredBinding>(FragmentClassRegisteredBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    private val fakeDataListClass = mutableListOf(
        CourseRegistration("1", "Android Class", null, "Nguyen Duy Minh Quan", null, null, null),
        CourseRegistration("2", "ReactJS Class", null, "Nguyen Thi Trang", null, null, null),
    )

    private val adapter = ListCourseAdapter()
    override fun initData() {
        adapter.setDataList(fakeDataListClass)
    }

    override fun bindData() {
        binding.rclListClassRegistered.adapter = adapter
        binding.rclListClassRegistered.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun observeData() {

    }

    override fun setOnClick() {

    }
}