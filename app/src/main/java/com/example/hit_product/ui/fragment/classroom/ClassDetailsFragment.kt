package com.example.hit_product.ui.fragment.classroom

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        get() = ViewModelProvider(this)[UserInformationViewModel::class.java]

    private val viewModelRegistration: RegistrationViewModel
        get() = ViewModelProvider(this)[RegistrationViewModel::class.java]

    private val customViewToast by lazy { CustomViewToast(requireContext()) }

    private var courseRegistration: CourseRegistration? = null
    private var userId: String? = null
    private var courseId: String? = null

    override fun initData() {
        requireActivity().getToken().let { viewModel.getAllCourse(it!!) }

        courseRegistration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("course", CourseRegistration::class.java)
        } else {
            arguments?.getSerializable("course") as CourseRegistration
        }

        requireActivity().getToken().let { viewModelUser.getUserInformation(it!!) }
    }


    override fun bindData() {
        courseRegistration?.apply {
            binding.className.text = name
            binding.leaderName.text = leader
            binding.introClass.text = detail
            Glide.with(requireContext()).load(picture).into(binding.avtLeader)
        }
        Log.d("Course Regis: ", courseRegistration.toString())
    }

    override fun observeData() {
        viewModelUser.userInformation.observe(viewLifecycleOwner, Observer { inf ->
            inf?.let {
                userId = it.id
                Log.d("User Id: ", userId.toString())
            }
        })
    }

    override fun setOnClick() {
        binding.btnClassRegistration.setOnClickListener {
            Log.d("UserId: ", userId.toString())
            courseId = courseRegistration?.id
            Log.d("Course Id: ", courseId.toString())
            val registerCourseRequest =
                RegisterCourseRequest(userId.toString(), courseId.toString())
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
                        } else {
                            customViewToast.makeText(
                                requireContext(),
                                "Đăng ký thất bại. Bạn chỉ được đăng ký tối đa 2 lớp học",
                                Toast.LENGTH_LONG.toLong(),
                                R.drawable.failure_icon_toast
                            ).show()
                        }
                    })
            }
        }
    }

}