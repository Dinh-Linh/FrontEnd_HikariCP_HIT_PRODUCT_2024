package com.example.hit_product.ui

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.source.Account
import com.example.hit_product.databinding.FragmentForgetEmailBinding

class ForgetEmailFragment : BaseFragment<FragmentForgetEmailBinding>(FragmentForgetEmailBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    private val listAccount = mutableListOf(
        Account("001", "user1", "password1", "2004", "2010", "2010"),
        Account("002", "user2", "password2", "2004", "2010", "2010"),
        Account("003", "user3", "password3", "2004", "2010", "2010"),
        Account("004", "user4", "password4", "2004", "2010", "2010"),
        Account("005", "user5", "password5", "2004", "2010", "2010")
    )

    override fun initData() {}

    override fun bindData() {}

    override fun observeData() {}

    override fun setOnClick() {

        binding.btnConfirmEmail.setOnClickListener {
            val userEmail = binding.edtEmail.text.toString()

            when {
                userEmail.isEmpty() -> {
                    showAlertDialog("Thông báo", "Vui lòng nhập mã sinh viên của bạn")
                }

                else -> {
                    val account = listAccount.find {
                        it.userID == userEmail
                    }
                    if (account != null) {
                        showAlertDialog("Thông báo", "Khôi phục mật khẩu thành công, mật khẩu của bạn là: ${account.password}")
                    } else {
                        showAlertDialog("Warning", "")
                    }
                }
            }
        }

        binding.btnBackToLogin.setOnClickListener{
            findNavController().navigate(R.id.action_forgetFragment_to_loginFragment)
        }
    }

    private fun showAlertDialog(title: String, message: String){
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_alert_dialog, null)
        val alertTitle = dialogView.findViewById<TextView>(R.id.alertTitle)
        val alertMessage = dialogView.findViewById<TextView>(R.id.alertMessage)
        val btnOk = dialogView.findViewById<Button>(R.id.btnOk)

        alertTitle.text = title
        alertMessage.text = message

        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()

        btnOk.setOnClickListener{
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
}
