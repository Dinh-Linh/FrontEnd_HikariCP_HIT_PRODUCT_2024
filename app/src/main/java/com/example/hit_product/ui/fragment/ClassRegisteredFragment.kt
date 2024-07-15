package com.example.hit_product.ui.fragment

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.ClassRegistration
import com.example.hit_product.databinding.FragmentClassRegisteredBinding
import com.example.hit_product.ui.adapter.ListClassAdapter

class ClassRegisteredFragment :
    BaseFragment<FragmentClassRegisteredBinding>(FragmentClassRegisteredBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    private val fakeDataListClass = mutableListOf(
        ClassRegistration("1", "Android Class", null, "Nguyen Duy Minh Quan", null, null, null),
        ClassRegistration("2", "ReactJS Class", null, "Nguyen Thi Trang", null, null, null),
    )

    private val adapter = ListClassAdapter()
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