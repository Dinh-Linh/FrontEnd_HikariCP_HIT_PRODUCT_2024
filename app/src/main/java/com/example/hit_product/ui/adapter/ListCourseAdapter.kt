package com.example.hit_product.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hit_product.base.BaseAdapter
import com.example.hit_product.base.BaseViewHolder
import com.example.hit_product.data.CourseRegistration
import com.example.hit_product.databinding.CourseItemBinding

class ListCourseAdapter :
    BaseAdapter<CourseRegistration, CourseItemBinding>(CourseItemBinding::inflate) {
    var onClick: ((CourseRegistration) -> Unit)? = null
    override fun bindData(binding: CourseItemBinding, item: CourseRegistration, position: Int) {
        binding.leader.text = item.leader
        binding.className.text = item.name
    }

    override fun onItemClick(binding: CourseItemBinding, item: CourseRegistration, position: Int) {
        binding.listClass.setOnClickListener {
            onClick?.invoke(item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CourseItemBinding> {
        val inflate = LayoutInflater.from(parent.context)
        val binding = CourseItemBinding.inflate(inflate, parent, false)
        return BaseViewHolder(binding)
    }

}