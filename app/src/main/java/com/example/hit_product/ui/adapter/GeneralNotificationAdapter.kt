package com.example.hit_product.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hit_product.base.BaseAdapter
import com.example.hit_product.base.BaseViewHolder
import com.example.hit_product.data.data_class.Notification
import com.example.hit_product.databinding.ClassNotificationBinding

class GeneralNotificationAdapter :
    BaseAdapter<Notification, ClassNotificationBinding>(ClassNotificationBinding::inflate){
        var onclick: ((Notification)->Unit)? = null
    override fun bindData(binding: ClassNotificationBinding, item: Notification, position: Int) {
        binding.nameClass.text = item.name
        binding.address.text = item.detail
    }

    override fun onItemClick(binding: ClassNotificationBinding, item: Notification, position: Int) {
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