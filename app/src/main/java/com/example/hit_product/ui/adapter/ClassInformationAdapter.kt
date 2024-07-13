package com.example.hit_product.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hit_product.base.BaseAdapter
import com.example.hit_product.base.BaseViewHolder
import com.example.hit_product.data.Information
import com.example.hit_product.databinding.ClassInformationBinding


class ClassInformationAdapter() : BaseAdapter<Information, ClassInformationBinding>(ClassInformationBinding::inflate){
    private val userInformation = mutableListOf<Information>()

    override fun bindData(binding: ClassInformationBinding, item: Information, position: Int) {
        binding.birth.text = item.birth
        binding.gen.text = item.gen
        binding.email.text = item.email
        binding.phone.text = item.phone
        binding.address.text = item.address
    }

    override fun onItemClick(binding: ClassInformationBinding, item: Information, position: Int) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ClassInformationBinding> {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ClassInformationBinding.inflate(inflate, parent, false)
        return BaseViewHolder(binding)
    }


}