package com.example.hit_product.ui

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView

import androidx.lifecycle.ViewModelProvider
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.source.Account
import com.example.hit_product.databinding.FragmentForgetBinding

class ForgetFragment : BaseFragment<FragmentForgetBinding>(FragmentForgetBinding::inflate) {
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
        binding.btnRestorePassword.setOnClickListener {
            val userID = binding.edtUserID.text.toString()
            val username = binding.edtUserName.text.toString()
            val dateOfBirth = binding.edtDateOfBirth.text.toString()
            val dateOfClub1 = binding.edtDateOfClub1.text.toString()
            val dateOfClub2 = binding.edtDateOfClub2.text.toString()

            when {
                userID.isEmpty() -> {
                    showAlertDialog("Thông báo", "Vui lòng nhập mã sinh viên của bạn")
                }
                username.isEmpty() -> {
                    showAlertDialog("Thông báo", "Vui lòng nhập họ và tên của bạn")
                }
                dateOfBirth.isEmpty() -> {
                    showAlertDialog("Thông báo", "Vui lòng nhập sinh nhật của bạn")
                }
                dateOfClub1.isEmpty() -> {
                    showAlertDialog("Thông báo", "Vui lòng nhập sinh nhật câu lạc bộ")
                }
                dateOfClub2.isEmpty() -> {
                    showAlertDialog("Thông báo", "Vui lòng xác nhận sinh nhật câu lạc bộ")
                }
                else -> {
                    val account = listAccount.find {
                        it.userID == userID &&
                                it.username == username &&
                                it.dateOfBirth == dateOfBirth &&
                                it.dateOfClub1 == dateOfClub1 &&
                                it.dateOfClub2 == dateOfClub2
                    }

                    if (account != null) {
                        showAlertDialog("Thông báo", "Khôi phục mật khẩu thành công, mật khẩu của bạn là: ${account.password}")
                    } else {
                        val errorMessages = mutableListOf<String>()
                        val matchingAccounts = listAccount.filter { it.userID == userID }
                        val matchingUsernames = matchingAccounts.filter { it.username == username }
                        val matchingDob = matchingUsernames.filter { it.dateOfBirth == dateOfBirth }
                        val matchingClub1 = matchingDob.filter { it.dateOfClub1 == dateOfClub1 }
                        val matchingClub2 = matchingClub1.filter { it.dateOfClub2 == dateOfClub2 }

                        if (matchingAccounts.isEmpty()) {
                            errorMessages.add("Mã sinh viên không chính xác")
                        }else if (matchingUsernames.isEmpty()) {
                            errorMessages.add("Họ và tên không chính xác")
                        }else if (matchingDob.isEmpty()) {
                            errorMessages.add("Sinh nhật không chính xác")
                        }else if (matchingClub1.isEmpty()) {
                            errorMessages.add("Sinh nhật câu lạc bộ không chính xác")
                        }else if (matchingClub2.isEmpty()) {
                            errorMessages.add("Xác nhận sinh nhật câu lạc bộ không trùng khớp")
                        }

                        val errorMessage = errorMessages.joinToString("\n")
                        showAlertDialog("Warning", errorMessage)
                    }
                }
            }
        }

        binding.btnBackToLogin.setOnClickListener {
            showAlertDialog("Thông báo", "Quay lại màn hình đăng nhập")
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
