package com.example.hit_product.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hit_product.base.BaseAdapter
import com.example.hit_product.base.BaseViewHolder
import com.example.hit_product.data.data_class.GeneralNotification
import com.example.hit_product.databinding.ClassPersonalNotificationBinding
import java.text.SimpleDateFormat
import java.util.Locale

class PersonalNotificationAdapter: BaseAdapter<GeneralNotification, ClassPersonalNotificationBinding>(ClassPersonalNotificationBinding::inflate){
    var onclick: ((GeneralNotification)->Unit?)? = null
    override fun bindData(
        binding: ClassPersonalNotificationBinding,
        item: GeneralNotification,
        position: Int
    ) {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
        val targetFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        try {
            val date = originalFormat.parse(item.sendDate)
            val formattedDate = targetFormat.format(date)
            binding.time.text = formattedDate
        } catch (e: Exception) {
            e.printStackTrace()
            binding.time.text = item.sendDate
        }
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