package com.example.hit_product.ui.fragment

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.Classes
import com.example.hit_product.databinding.FragmentHomeBinding
import com.example.hit_product.ui.adapter.ClassTodayAdapter
import com.example.hit_product.ui.view_model.HomeViewModel
import com.example.hit_product.utils.extension.getToken
import java.util.Calendar

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this)[HomeViewModel::class.java]

    private val classAdapter = ClassTodayAdapter()
    private val listClass = mutableListOf(
        Classes(name = "Math 101",
            location = "Room 201",
            startTime = Calendar.getInstance()
                .apply { set(Calendar.HOUR_OF_DAY, 11); set(Calendar.MINUTE, 0) }.time,
            endTime = Calendar.getInstance()
                .apply { set(Calendar.HOUR_OF_DAY, 13); set(Calendar.MINUTE, 0) }.time
        ),
        Classes(name = "History 101",
            location = "Room 202",
            startTime = Calendar.getInstance()
                .apply { set(Calendar.HOUR_OF_DAY, 11); set(Calendar.MINUTE, 0) }.time,
            endTime = Calendar.getInstance()
                .apply { set(Calendar.HOUR_OF_DAY, 13); set(Calendar.MINUTE, 0) }.time
        ),
        Classes(name = "Physic 101",
            location = "Room 203",
            startTime = Calendar.getInstance()
                .apply { set(Calendar.HOUR_OF_DAY, 11); set(Calendar.MINUTE, 0) }.time,
            endTime = Calendar.getInstance()
                .apply { set(Calendar.HOUR_OF_DAY, 13); set(Calendar.MINUTE, 0) }.time
        ),

        )

    override fun initData() {
        requireActivity().getToken()?.let { viewModel.getAllClass(it) }
        classAdapter.setDataList(listClass)
    }

    override fun bindData() {
        binding.rclToday.adapter = classAdapter
        binding.rclToday.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun observeData() {

    }

    override fun setOnClick() {

    }
}