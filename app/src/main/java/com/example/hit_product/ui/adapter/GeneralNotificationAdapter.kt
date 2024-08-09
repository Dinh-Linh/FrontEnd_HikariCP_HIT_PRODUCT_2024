package com.example.hit_product.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hit_product.base.BaseAdapter
import com.example.hit_product.base.BaseViewHolder
import com.example.hit_product.data.data_class.GeneralNotification
import com.example.hit_product.databinding.ClassNotificationBinding

class GeneralNotificationAdapter :
    BaseAdapter<GeneralNotification, ClassNotificationBinding>(ClassNotificationBinding::inflate){
        var onclick: ((GeneralNotification)->Unit)? = null
    override fun bindData(binding: ClassNotificationBinding, item: GeneralNotification, position: Int) {
        binding.sendTime.text = item.sendDate
        binding.nameClass.text = item.name
        binding.address.text = item.detail
    }

    override fun onItemClick(binding: ClassNotificationBinding, item: GeneralNotification, position: Int) {
        binding.listGeneralNotification.setOnClickListener{
            onclick?.invoke(item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ClassNotificationBinding> {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ClassNotificationBinding.inflate(inflate, parent, false)
        return BaseViewHolder(binding)
    }

}