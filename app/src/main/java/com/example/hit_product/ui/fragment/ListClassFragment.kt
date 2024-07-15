package com.example.hit_product.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.ClassRegistration
import com.example.hit_product.databinding.FragmentListClassBinding
import com.example.hit_product.ui.adapter.ListClassAdapter


class ListClassFragment : BaseFragment<FragmentListClassBinding>(FragmentListClassBinding::inflate) {
    override val viewModel : BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    private val fakeDataListClass = mutableListOf(
        ClassRegistration("1", "Android Class", null, "Nguyen Duy Minh Quan", null, null, null),
        ClassRegistration("2", "ReactJS Class", null, "Nguyen Thi Trang", null, null, null),
        ClassRegistration("3", "Java SpringBoot Class", null, "Nguyen Tien Kien", null, null, null)
    )

    private val adapter = ListClassAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        bindData()
    }
    override fun initData() {
        adapter.setDataList(fakeDataListClass)
    }

    override fun bindData() {
        binding.rclListClass.adapter = adapter
        binding.rclListClass.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun observeData() {

    }

    override fun setOnClick() {

    }
}