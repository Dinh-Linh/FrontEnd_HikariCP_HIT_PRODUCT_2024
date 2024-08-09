package com.example.hit_product.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hit_product.base.BaseAdapter
import com.example.hit_product.base.BaseViewHolder
import com.example.hit_product.data.data_class.GeneralNotification
import com.example.hit_product.databinding.ClassPersonalNotificationBinding

class PersonalNotificationAdapter: BaseAdapter<GeneralNotification, ClassPersonalNotificationBinding>(ClassPersonalNotificationBinding::inflate){
    var onclick: ((GeneralNotification)->Unit?)? = null
    override fun bindData(
        binding: ClassPersonalNotificationBinding,
        item: GeneralNotification,
        position: Int
    ) {
        binding.time.text = item.sendDate
        binding.name.text = item.name
        binding.detail.text = item.detail
    }

    override fun onItemClick(
        binding: ClassPersonalNotificationBinding,
        item: GeneralNotification,
        position: Int
    ) {
        binding.listPersonalNotification.setOnClickListener {
            onclick?.invoke(item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ClassPersonalNotificationBinding> {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ClassPersonalNotificationBinding.inflate(inflate, parent, false)
        return BaseViewHolder(binding)
    }


}