package com.example.hit_product.ui.fragment


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.databinding.FragmentHomeBinding
import com.example.hit_product.ui.adapter.ClassTodayAdapter
import com.example.hit_product.ui.view_model.HomeViewModel
import com.example.hit_product.utils.extension.getToken

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this)[HomeViewModel::class.java]

    private val classAdapter = ClassTodayAdapter()

    override fun initData() {
        requireActivity().getToken()?.let { viewModel.getAllClass(it) }
    }

    override fun bindData() {
        binding.rclToday.adapter = classAdapter
        binding.rclToday.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun observeData() {
        viewModel.classes.observe(viewLifecycleOwner, Observer {
            classAdapter.setDataList(it.toMutableList())
        })
    }

    override fun setOnClick() {

    }
}