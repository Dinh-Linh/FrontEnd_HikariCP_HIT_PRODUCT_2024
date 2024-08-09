package com.example.hit_product.ui.fragment.auth


import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hit_product.R
import com.example.hit_product.data.data_class.ChangePasswordRequest
import com.example.hit_product.databinding.FragmentChangePasswordBinding
import com.example.hit_product.ui.CustomViewToast
import com.example.hit_product.ui.DialogChangePasswordFailure
import com.example.hit_product.ui.view_model.ChangePasswordViewModel
import com.example.hit_product.ui.view_model.UserInformationViewModel
import com.example.hit_product.utils.extension.getToken


class ChangePasswordFragment : DialogFragment(){

    private lateinit var binding: FragmentChangePasswordBinding
    private lateinit var memberId: String

    private val viewModel: ChangePasswordViewModel
        get() = ViewModelProvider(this)[ChangePasswordViewModel::class.java]

    private val viewModel2: UserInformationViewModel
        get() = ViewModelProvider(this)[UserInformationViewModel::class.java]

    private val changePWDialogFailure by lazy { DialogChangePasswordFailure(requireContext()) }
    private val customViewToast by lazy { CustomViewToast(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val params = dialog?.window?.attributes
        params?.gravity = android.view.Gravity.TOP
        params?.y = 250
        dialog?.window?.attributes = params
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        observeData()
        setOnClick()
    }

    private fun initData() {
        requireActivity().getToken()?.let { viewModel2.getUserInformation(it) }
    }


    private fun observeData() {
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                changePWDialogFailure.show()
            }
        }
        viewModel2.userInformation.observe(viewLifecycleOwner, Observer { informationList ->
            if (informationList != null) {
                memberId = informationList.id
                Log.d("Information List", "Member id: $memberId")
            } else {
                Log.d("Information List", "is Empty")
            }
        })
    }

    private fun setOnClick() {
        binding.btnConfirmNewPassword.setOnClickListener {
            val oldPassword = binding.edtOldPassword.text.toString()
            val newPassword = binding.edtNewPassword.text.toString()
            val confirmNewPassword = binding.edtConfirmNewPassword.text.toString()
            val changePasswordRequest = ChangePasswordRequest(memberId, oldPassword, newPassword)
            val token = requireActivity().getToken()
            if (newPassword == confirmNewPassword) {
                viewModel.changePassword(token!!,
                    changePasswordRequest,
                    onChangePWSuccess = { apiResponse ->
                        val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
                        pref.edit().putString("token", "Bearer ${apiResponse.data?.accessToken}").commit()

                        customViewToast.makeText(requireContext(), "Đổi mật khẩu thành công!", Toast.LENGTH_LONG.toLong(), R.drawable.success_icon_toast).show()
                    }
                )
            } else {
                customViewToast.makeText(requireContext(), "Đổi mật khẩu thất bại!", Toast.LENGTH_LONG.toLong(), R.drawable.failure_icon_toast).show()
            }
        }
        binding.btnCancelChangePassword.setOnClickListener {
            dialog?.dismiss()
        }
    }

}