    package com.example.hit_product.ui.fragment.auth


    import android.content.Context.MODE_PRIVATE
    import android.util.Log
    import android.widget.Toast
    import androidx.lifecycle.Observer
    import androidx.lifecycle.ViewModelProvider
    import androidx.navigation.fragment.findNavController
    import com.example.hit_product.R
    import com.example.hit_product.base.BaseFragment
    import com.example.hit_product.data.data_class.ChangePasswordRequest
    import com.example.hit_product.databinding.FragmentChangePasswordBinding
    import com.example.hit_product.ui.DialogChangePasswordFailure
    import com.example.hit_product.ui.view_model.ChangePasswordViewModel
    import com.example.hit_product.ui.view_model.UserInformationViewModel
    import com.example.hit_product.utils.extension.getToken


    class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>(FragmentChangePasswordBinding::inflate) {
        override val viewModel: ChangePasswordViewModel
            get() = ViewModelProvider(this)[ChangePasswordViewModel::class.java]

        private val viewModel2 : UserInformationViewModel
            get() = ViewModelProvider(this)[UserInformationViewModel::class.java]

        private val changePWDialogFailure by lazy{ DialogChangePasswordFailure(requireContext())}

        override fun initData() {
            requireActivity().getToken()?.let{viewModel2.getUserInformation(it)}
        }

        override fun bindData() {

        }

        override fun observeData() {
            viewModel.error.observe(viewLifecycleOwner){
                if(it != null){
                    changePWDialogFailure.show()
                }
            }

        }

        override fun setOnClick() {
            binding.btnConfirmNewPassword.setOnClickListener {
                val oldPassword = binding.edtOldPassword.text.toString()
                val newPassword = binding.edtNewPassword.text.toString()
                val confirmNewPassword = binding.edtConfirmNewPassword.text.toString()

                viewModel2.userInformation.observe(viewLifecycleOwner, Observer { informationList ->
                    if(informationList == null){
                        Log.d("Information List", "is Empty")
                    }else {
                        val memberId = informationList.id
                        Log.d("Information List", "is Ready")
                        val changePasswordRequest = ChangePasswordRequest(memberId, oldPassword, newPassword)
                        if (newPassword == confirmNewPassword){
                            viewModel.changePassword(
                                changePasswordRequest,
                                onChangePWSucces = {apiResponse ->
                                    val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
                                    pref.edit().putString("token", "Bearer ${apiResponse.data?.accessToken}").commit()
                                    context?.let {
                                        Toast.makeText(it, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            )
                        }else{
                            context?.let {
                                Toast.makeText(it, "Thông tin không khớp", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })

                binding.btnCancelChangePassword.setOnClickListener {
                    findNavController().navigate(R.id.action_changePasswordFragment_to_accountFragment)
                }


            }
        }


    }