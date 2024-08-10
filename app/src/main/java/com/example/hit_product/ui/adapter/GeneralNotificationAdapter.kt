package com.example.hit_product.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hit_product.base.BaseAdapter
import com.example.hit_product.base.BaseViewHolder
import com.example.hit_product.data.data_class.GeneralNotification
import com.example.hit_product.databinding.ClassNotificationBinding
import java.text.SimpleDateFormat
import java.util.Locale

class GeneralNotificationAdapter :
    BaseAdapter<GeneralNotification, ClassNotificationBinding>(ClassNotificationBinding::inflate){
        var onclick: ((GeneralNotification)->Unit)? = null
    override fun bindData(binding: ClassNotificationBinding, item: GeneralNotification, position: Int) {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
        val targetFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        try {
            val date = originalFormat.parse(item.sendDate)
            val formattedDate = targetFormat.format(date)
            binding.sendTime.text = formattedDate
        } catch (e: Exception) {
            e.printStackTrace()
            binding.sendTime.text = item.sendDate
        }
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