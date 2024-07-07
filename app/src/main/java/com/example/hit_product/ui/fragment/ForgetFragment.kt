package com.example.hit_product.ui.fragment

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.source.Members
import com.example.hit_product.databinding.FragmentForgetBinding

class ForgetFragment : BaseFragment<FragmentForgetBinding>(FragmentForgetBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    private val listMembers = mutableListOf(
        Members("1", null, null, null, null, null, null, null, null, null, null, "user1", "pass1", null, null, null),
        Members("2", null, null, null, null, null, null, null, null, null, null, "user2", "pass2", null, null, null),
        Members("3", null, null, null, null, null, null, null, null, null, null, "user3", "pass3", null, null, null),
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
                    val account = listMembers.find {
                        it.id == userID &&
                                it.username == username &&
                                it.birth == dateOfBirth &&
                                it.birth == dateOfClub1 &&
                                it.birth == dateOfClub2
                    }

                    if (account != null) {
                        showAlertDialog("Thông báo", "Khôi phục mật khẩu thành công, mật khẩu của bạn là: ${account.password}")
                    } else {
                        val errorMessages = mutableListOf<String>()
                        val matchingAccounts = listMembers.filter { it.id == userID }
                        val matchingUsernames = matchingAccounts.filter { it.username == username }
                        val matchingDob = matchingUsernames.filter { it.birth == dateOfBirth }
                        val matchingClub1 = matchingDob.filter { it.birth == dateOfClub1 }
                        val matchingClub2 = matchingClub1.filter { it.birth == dateOfClub2 }

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
