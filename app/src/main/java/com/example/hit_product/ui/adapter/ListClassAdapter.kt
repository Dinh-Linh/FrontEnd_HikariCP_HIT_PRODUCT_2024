package com.example.hit_product.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hit_product.base.BaseAdapter
import com.example.hit_product.base.BaseViewHolder
import com.example.hit_product.data.ClassRegistration
import com.example.hit_product.databinding.ClassItemBinding

class ListClassAdapter :
    BaseAdapter<ClassRegistration, ClassItemBinding>(ClassItemBinding::inflate) {
    override fun bindData(binding: ClassItemBinding, item: ClassRegistration, position: Int) {
        binding.leader.text = item.leader
        binding.className.text = item.name
    }

    override fun onItemClick(binding: ClassItemBinding, item: ClassRegistration, position: Int) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ClassItemBinding> {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ClassItemBinding.inflate(inflate, parent, false)
        return BaseViewHolder(binding)
    }

}