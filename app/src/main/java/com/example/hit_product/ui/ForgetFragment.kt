package com.example.hit_product.ui


import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.source.Account

import com.example.hit_product.databinding.FragmentForgetBinding


class ForgetFragment : BaseFragment<FragmentForgetBinding>(FragmentForgetBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    private val dialog by lazy { Dialog(requireContext()) }
    private val toast by lazy{ToastView(requireContext())}
    private val listAccount = mutableListOf(
        Account("001", "username1", "2004", "2010", "2010"),
        Account("002", "username2", "2004", "2010", "2010"),
        Account("003", "username3", "2004", "2010", "2010"),
        Account("004", "username4", "2004", "2010", "2010"),
        Account("005", "username5", "2004", "2010", "2010")
    )

    override fun initData() {

    }

    override fun bindData() {

    }

    override fun observeData() {

    }

    override fun setOnClick() {
        binding.btnRestorePassword.setOnClickListener{
            val userID = binding.edtUserID.text.toString()
            val username = binding.edtUserName.text.toString()
            val dateOfBirth = binding.edtDateOfBirth.text.toString()
            val dateOfClub1 = binding.edtDateOfClub1.text.toString()
            val dateOfClub2 = binding.edtDateOfClub2.text.toString()
            if(userID.isEmpty() && username.isNotEmpty() && dateOfBirth.isNotEmpty() && dateOfClub1.isNotEmpty() && dateOfClub2.isNotEmpty()){
                binding.btnRestorePassword.postDelayed({
                    val isAccountValid = listAccount.any { it.userID == userID && it.username == username && it.dateOfBirth  == dateOfBirth && it.dateOfClub1 == dateOfClub1 && it.dateOfClub2 == dateOfClub2 }
                    if(isAccountValid == true){
                        toast.makeText(
                            requireContext(),
                            "Khoi phuc mat khau thanh cong"
                        ).show()
                    }
                }, 2000)
            }else{
                toast.makeText(
                    requireContext(),
                    "Vui long nhap day du thong tin"
                ).show()
            }
        }
    }
}