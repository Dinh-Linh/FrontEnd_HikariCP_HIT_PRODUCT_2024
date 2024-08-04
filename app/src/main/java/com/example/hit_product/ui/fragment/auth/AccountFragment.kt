package com.example.hit_product.ui.fragment.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hit_product.R
import com.example.hit_product.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    private val binding by lazy { FragmentAccountBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnBackToSetting.setOnClickListener{
            findNavController().navigate(R.id.action_accountFragment_to_settingFragment)
        }
    }

}