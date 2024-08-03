package com.example.hit_product.ui.fragment.main_app

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.source.local.Converters
import com.example.hit_product.databinding.FragmentHomeBinding
import com.example.hit_product.ui.adapter.ClassTodayAdapter
import com.example.hit_product.ui.view_model.HomeViewModel
import com.example.hit_product.ui.view_model.UserInformationViewModel
import com.example.hit_product.utils.extension.getToken
import java.util.Calendar

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this)[HomeViewModel::class.java]

    private val viewModel2 : UserInformationViewModel
        get() = ViewModelProvider(this)[UserInformationViewModel::class.java]


    private val classAdapter = ClassTodayAdapter()


    override fun initData() {
        val currentDate = Calendar.getInstance().time
        val formatDate = Converters().getCurrentDate(currentDate)
        Log.d("currentDate: ", "$currentDate")
        Log.d("formatDate: ", formatDate)
        requireActivity().getToken()?.let { viewModel.getClassByDay(formatDate, "Class", it) }

        requireActivity().getToken()?.let { viewModel2.getUserInformation(it) }

    }

    override fun bindData() {
        binding.rclToday.adapter = classAdapter
        binding.rclToday.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    @SuppressLint("SetTextI18n")
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

        viewModel2.userInformation.observe(viewLifecycleOwner, Observer { inf ->
            inf?.let {
                binding.memberName.text = "Hi ${it.fullName}"
            }
        })
    }

    override fun setOnClick() {
        binding.timeTable.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_timetableFragment)
        }

        binding.schedule.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_classRegistrationFragment)
        }
        binding.btnNotification.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
        }
    }
}