package com.example.hit_product.ui.fragment

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.databinding.FragmentInformationBinding
import com.example.hit_product.ui.adapter.UserInformationAdapter
import com.example.hit_product.ui.view_model.UserInformationViewModel
import com.example.hit_product.utils.extension.getToken

class UserInformationFragment : BaseFragment<FragmentInformationBinding>(FragmentInformationBinding::inflate) {
    override val viewModel: UserInformationViewModel
        get() = ViewModelProvider(this)[UserInformationViewModel::class.java]

    private val informationAdapter = UserInformationAdapter()

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