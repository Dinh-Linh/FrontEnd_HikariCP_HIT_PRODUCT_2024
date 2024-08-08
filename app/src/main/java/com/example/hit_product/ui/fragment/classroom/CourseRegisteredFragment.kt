package com.example.hit_product.ui.fragment.classroom

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.data_class.CourseRegistration
import com.example.hit_product.databinding.FragmentClassRegisteredBinding
import com.example.hit_product.ui.adapter.ListCourseAdapter
import com.example.hit_product.ui.view_model.RegistrationViewModel
import com.example.hit_product.ui.view_model.UserInformationViewModel
import com.example.hit_product.utils.extension.getToken

class CourseRegisteredFragment :
    BaseFragment<FragmentClassRegisteredBinding>(FragmentClassRegisteredBinding::inflate) {
    override val viewModel: RegistrationViewModel
        get() = ViewModelProvider(this)[RegistrationViewModel::class.java]

    private val viewModelUser: UserInformationViewModel
        get() = ViewModelProvider(requireActivity())[UserInformationViewModel::class.java]

    private var fullname: String? = null
    private val adapter = ListCourseAdapter()
    private var totalAcceptedCourse = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        bindData()
    }

    override fun initData() {

    }

    override fun bindData() {
        binding.rclListClassRegistered.adapter = adapter
        binding.rclListClassRegistered.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    @SuppressLint("SetTextI18n")
    override fun observeData() {
        viewModelUser.userInformation.observe(viewLifecycleOwner, Observer { inf ->
            inf?.let {
                fullname = it.fullName
                requireActivity().getToken()
                    .let {
                        viewModel.getRegisteredByName(it!!, fullname!!)
                    }
            }
        })

        viewModel.listRegistered.observe(viewLifecycleOwner, Observer { registeredCourse ->
            registeredCourse?.let {
                val acceptedCourse =
                    it.filter { registeredCourse -> registeredCourse.status == "ACCEPT" }
                val courses = acceptedCourse.map { registeredCourse ->
                    CourseRegistration(
                        id = registeredCourse.courseId,
                        name = registeredCourse.name!!,
                        leader = registeredCourse.leader!!
                    )
                }
                Log.d("Course: ", courses.toString())
                adapter.setDataList(courses.toMutableList())
            }
        })

        viewModel.countAcceptRegistered.observe(viewLifecycleOwner, Observer { total ->
            binding.totalRegis.text = "${total}/2"
        })

    }

    override fun setOnClick() {

    }
}