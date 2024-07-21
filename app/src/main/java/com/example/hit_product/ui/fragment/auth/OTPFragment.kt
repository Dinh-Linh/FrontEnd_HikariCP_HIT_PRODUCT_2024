package com.example.hit_product.ui.fragment.auth


import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.databinding.FragmentOTPBinding
import com.example.hit_product.ui.DialogOTPFailure
import com.example.hit_product.ui.view_model.OTPViewModel


class OTPFragment : BaseFragment<FragmentOTPBinding>(FragmentOTPBinding::inflate) {
    override val viewModel: OTPViewModel
        get() = ViewModelProvider(this)[OTPViewModel::class.java]


    private val otpDialogFailure by lazy { DialogOTPFailure(requireContext())}

    override fun initData() {}

    override fun bindData() {}

    override fun observeData() {
        viewModel.error.observe(viewLifecycleOwner){
            if(it != null){
                otpDialogFailure.show()
            }
        }
    }

    override fun setOnClick() {
        binding.btnConfirmOTP.setOnClickListener {
            val otp = binding.edtOTP.text.toString()

        }

        binding.btnBackToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_OTPFragment_to_emailFragment)
        }
    }
}
