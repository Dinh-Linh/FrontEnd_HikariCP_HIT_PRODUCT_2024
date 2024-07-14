package com.example.hit_product.ui.fragment

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.Information
import com.example.hit_product.databinding.FragmentForgetBinding
import com.example.hit_product.databinding.FragmentInformationBinding
import com.example.hit_product.ui.adapter.ClassInformationAdapter
import com.example.hit_product.ui.view_model.InformationViewModel
import com.example.hit_product.utils.extension.getToken

class InformationFragment : BaseFragment<FragmentInformationBinding>(FragmentInformationBinding::inflate) {
    override val viewModel: InformationViewModel
        get() = ViewModelProvider(this)[InformationViewModel::class.java]

    private val informationAdapter = ClassInformationAdapter()

    override fun initData() {
        requireActivity().getToken()?.let{
            viewModel.getUserInformation(it)
        }
    }

    override fun bindData() {
        binding.rclUserInformation.adapter = informationAdapter
        binding.rclUserInformation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun observeData() {
        viewModel.userInformation.observe(viewLifecycleOwner, Observer { informationList ->
            if(informationList == null){
                binding.rclUserInformation.visibility = View.GONE
                Log.d("Information List", "is Empty")
            }else{
                binding.rclUserInformation.visibility = View.VISIBLE
                informationAdapter.setDataList(listOf(informationList).toMutableList())
                Log.d("Information List", "is Ready")
            }
        })
    }


    override fun setOnClick() {

    }

}