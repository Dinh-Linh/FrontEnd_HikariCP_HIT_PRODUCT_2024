package com.example.hit_product.ui.fragment.auth

import android.content.Context.MODE_PRIVATE
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

    override fun setOnClick() {
        binding.btnlogOut.setOnClickListener {
            val token = requireActivity().getToken()
            if (token != null) {
                viewModel.logOut(token)
                findNavController().navigate(R.id.action_settingFragment_to_loginFragment)
            }
        }

        binding.btnSwitchSavePassword.setOnCheckedChangeListener { _, isChecked ->
            val pref = requireActivity().getSharedPreferences("account", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putBoolean("save_password", isChecked)
            if (isChecked) {
                val savedUsername = pref.getString("saved_username", "")
                val savedPassword = pref.getString("saved_password", "")
                editor.apply()
                context?.let {
                    Toast.makeText(it, "Lưu thành công", Toast.LENGTH_SHORT).show()
                }
            } else {
                editor.remove("saved_username")
                editor.remove("saved_password")
                editor.apply()
                context?.let {
                    Toast.makeText(it, "Xóa thành công", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
