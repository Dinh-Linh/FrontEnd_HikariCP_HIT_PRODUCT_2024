package com.example.hit_product.ui.fragment

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.Classes
import com.example.hit_product.data.source.local.Converters
import com.example.hit_product.databinding.FragmentHomeBinding
import com.example.hit_product.ui.adapter.ClassTodayAdapter
import com.example.hit_product.ui.view_model.HomeViewModel
import com.example.hit_product.utils.extension.getToken
import java.util.Calendar
import java.util.Date

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this)[HomeViewModel::class.java]

    private val classAdapter = ClassTodayAdapter()


    override fun initData() {
        val currentDate = Calendar.getInstance().time
        val formatDate = Converters().getCurrentDate(currentDate)
        Log.d("currentDate: ", "$currentDate")
        Log.d("formatDate: ", formatDate)
        requireActivity().getToken()?.let { viewModel.getClassByDay(formatDate, "Class", it) }
    }

    override fun bindData() {
        binding.rclToday.adapter = classAdapter
        binding.rclToday.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun observeData() {
        viewModel.classes.observe(viewLifecycleOwner, Observer { classList ->
            if (classList == null) {
                binding.tvNoEvent.visibility = View.VISIBLE
                binding.rclToday.visibility = View.GONE
                Log.d("Class list", " is empty")
            } else {
                binding.tvNoEvent.visibility = View.GONE
                binding.rclToday.visibility = View.VISIBLE
                classAdapter.setDataList(listOf(classList).toMutableList())
                Log.d("Class list", " is available")
            }
        })
    }

    override fun setOnClick() {
        binding.timeTable.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_timetableFragment)
        }
    }
}