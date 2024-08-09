package com.example.hit_product.ui.fragment.auth

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.databinding.FragmentSettingBinding
import com.example.hit_product.ui.view_model.SettingViewModel
import com.example.hit_product.utils.extension.getToken

class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {
    override val viewModel: SettingViewModel
        get() = ViewModelProvider(this)[SettingViewModel::class.java]

    override fun initData() {}

    override fun bindData() {}

    override fun observeData() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
        val isSavePasswordEnabled = pref.getBoolean("save_password", false)
        binding.btnSwitchSavePassword.isChecked = isSavePasswordEnabled
        Log.d("SettingFragment", "Trạng thái lưu mật khẩu : $isSavePasswordEnabled")
    }

    override fun setOnClick() {
        binding.btnlogOut.setOnClickListener {
            val token = requireActivity().getToken()
            if (token != null) {
                viewModel.logOut(token)
                findNavController().navigate(R.id.action_settingFragment_to_loginFragment)
            }
        }
        binding.btnAccount.setOnClickListener{
            findNavController().navigate(R.id.action_settingFragment_to_accountFragment)
        }
        binding.btnDonate.setOnClickListener {

        }

        binding.btnSwitchSavePassword.setOnCheckedChangeListener { _, isChecked ->
            val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putBoolean("save_password", isChecked).commit()
            if (isChecked) {
                val savedUsername = pref.getString("saved_username", "")
                val savedPassword = pref.getString("saved_password", "")
                Log.d("SettingFragment", "Switch is checked. Username: $savedUsername, Password: $savedPassword")
                Log.d("SettingFragment", "Da luu mat khau thanh cong")
            } else {
                editor.remove("saved_username").commit()
                editor.remove("saved_password").commit()
                Log.d("SettingFragment", "Xoa mat khau thanh cong")
            }
            Log.d("SettingFragment", "Trạng thái lưu mật khẩu : $isChecked")
        }
    }

}
