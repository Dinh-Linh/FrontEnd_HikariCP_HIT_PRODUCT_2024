package com.example.hit_product.ui.fragment.classroom

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.data.data_class.CourseRegistration
import com.example.hit_product.data.data_class.RegisterCourseRequest
import com.example.hit_product.databinding.FragmentClassDetailsBinding
import com.example.hit_product.ui.CustomViewToast
import com.example.hit_product.ui.view_model.ListCourseViewModel
import com.example.hit_product.ui.view_model.RegistrationViewModel
import com.example.hit_product.ui.view_model.UserInformationViewModel
import com.example.hit_product.utils.extension.getToken

class ClassDetailsFragment :
    BaseFragment<FragmentClassDetailsBinding>(FragmentClassDetailsBinding::inflate) {
    override val viewModel: ListCourseViewModel
        get() = ViewModelProvider(this)[ListCourseViewModel::class.java]

    private val viewModelUser: UserInformationViewModel
        get() = ViewModelProvider(requireActivity())[UserInformationViewModel::class.java]

    private val viewModelRegistration: RegistrationViewModel
        get() = ViewModelProvider(this)[RegistrationViewModel::class.java]

    private val customViewToast by lazy { CustomViewToast(requireContext()) }

    private var courseRegistration: CourseRegistration? = null
    private var subscriberId: String? = null
    private var fullname: String? = null
    private var courseId: String? = null
    private var isRegistered: Boolean = false

    override fun initData() {
        requireActivity().getToken().let {
            viewModel.getAllCourse(it!!)
            viewModelUser.getUserInformation(it)
        }

        courseRegistration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("course", CourseRegistration::class.java)
        } else {
            arguments?.getSerializable("course") as CourseRegistration
        }
    }


    override fun bindData() {
        courseRegistration?.apply {
            binding.className.text = name
            binding.leaderName.text = leader
            binding.introClass.text = detail
            Glide.with(requireContext()).load(picture).into(binding.avtLeader)
        }
    }

    override fun observeData() {

        viewModelUser.userInformation.observe(viewLifecycleOwner, Observer { inf ->
            inf?.let {
                subscriberId = it.id
                Log.d("Subscriber Id: ", subscriberId.toString())
                fullname = it.fullName
                requireActivity().getToken()
                    .let { viewModelRegistration.getRegisteredByName(it!!, fullname!!) }
            }
        })
        viewModelRegistration.listRegistered.observe(
            viewLifecycleOwner,
            Observer { registeredCourse ->
                registeredCourse?.let { courses ->
                    courses.forEach { course ->
                        Log.d(
                            "Registered Course",
                            "ID Registered: ${course.id}, Status: ${course.status}"
                        )
                        if (course.courseId == courseId) {
                            when (course.status) {
                                "PENDING" -> {
                                    binding.btnClassRegistration.text =
                                        context?.getString(R.string.pending)
                                    isRegistered = true
                                }

                                "ACCEPT" -> {
                                    binding.btnClassRegistration.text =
                                        context?.getString(R.string.registered)
                                    isRegistered = true
                                }
                            }
                        }
                    }
                }
            })
    }

    override fun setOnClick() {
        courseId = courseRegistration?.id
        binding.btnClassRegistration.setOnClickListener {
            val registerCourseRequest =
                RegisterCourseRequest(subscriberId.toString(), courseId.toString())
            if (isRegistered) {
                Log.d("Text Button: ", binding.btnClassRegistration.text.toString())
                if (binding.btnClassRegistration.text == context?.getString(R.string.registered)) {
                    customViewToast.makeText(
                        requireContext(),
                        "Bạn đã đăng ký khoá học này",
                        Toast.LENGTH_LONG.toLong(),
                        R.drawable.success_icon_toast
                    ).show()
                } else {
                    customViewToast.makeText(
                        requireContext(),
                        "Bạn đã đăng ký khoá học này. Đang chờ xử lý",
                        Toast.LENGTH_LONG.toLong(),
                        R.drawable.warning_icon_toast
                    ).show()
                }

            } else if (viewModelRegistration.countRegistered.value == 2) {
                customViewToast.makeText(
                    requireContext(),
                    "Đăng ký thất bại. Mỗi thành viên chỉ được đăng ký tối đa 2 lớp học",
                    Toast.LENGTH_LONG.toLong(),
                    R.drawable.failure_icon_toast
                ).show()
            } else {
                requireActivity().getToken()?.let { token ->
                    viewModelRegistration.registerCourse(
                        token,
                        registerCourseRequest,
                        onRegisterCourseResponse = { response ->
                            if (response.status == "PENDING") {
                                customViewToast.makeText(
                                    requireContext(),
                                    "Đăng ký thành công. Đang chờ xử lý",
                                    Toast.LENGTH_LONG.toLong(),
                                    R.drawable.success_icon_toast
                                ).show()
                                binding.btnClassRegistration.text =
                                    context?.getString(R.string.pending)
                                isRegistered = true
                            }
                        })
                }
            }

        }

        binding.btnCancelRegistration.setOnClickListener {
            if (isRegistered) {
                val registeredId =
                    viewModelRegistration.listRegistered.value?.find { it.courseId == courseId }?.id
                requireActivity().getToken().let { token ->
                    viewModelRegistration.cancelRegistered(
                        token!!,
                        registeredId!!,
                        subscriberId!!,
                        onCancelRegisteredResponse = { response ->
                            if (response.message == "success") {
                                customViewToast.makeText(
                                    requireContext(),
                                    "Huỷ đăng ký thành công",
                                    Toast.LENGTH_LONG.toLong(),
                                    R.drawable.success_icon_toast
                                ).show()
                                isRegistered = false
                            }
                        })
                }
            } else {
                customViewToast.makeText(
                    requireContext(),
                    "Bạn chưa đăng ký lớp học này",
                    Toast.LENGTH_LONG.toLong(),
                    R.drawable.failure_icon_toast
                ).show()
            }
        }

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_classInformationFragment_to_classRegistrationFragment)
        }
    }


}