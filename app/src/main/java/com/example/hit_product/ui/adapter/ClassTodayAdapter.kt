package com.example.hit_product.ui.adapter

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hit_product.base.BaseAdapter
import com.example.hit_product.base.BaseViewHolder
import com.example.hit_product.data.data_class.Classes
import com.example.hit_product.databinding.ClassTodayBinding
import java.util.Locale

class ClassTodayAdapter() : BaseAdapter<Classes, ClassTodayBinding>(ClassTodayBinding::inflate) {

    private val classList = mutableListOf<Classes>()
    @SuppressLint("SetTextI18n")
    override fun bindData(binding: ClassTodayBinding, item: Classes, position: Int) {
        val dateFormat = SimpleDateFormat("HH.mm", Locale.getDefault())
        binding.className.text = item.name
        binding.location.text = item.location
        //binding.time.text = "${dateFormat.format(item.startTime)} - ${dateFormat.format(item.endTime)}"
    }

    override fun onItemClick(binding: ClassTodayBinding, item: Classes, position: Int) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ClassTodayBinding> {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ClassTodayBinding.inflate(inflate, parent, false)
        return BaseViewHolder(binding)
    }

}