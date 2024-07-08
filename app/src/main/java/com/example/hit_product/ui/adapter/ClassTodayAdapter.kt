package com.example.hit_product.ui.adapter

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import com.example.hit_product.base.BaseAdapter
import com.example.hit_product.data.Classes
import com.example.hit_product.databinding.ClassTodayBinding
import java.util.Locale

class ClassTodayAdapter() : BaseAdapter<Classes, ClassTodayBinding>(ClassTodayBinding::inflate) {
    @SuppressLint("SetTextI18n")
    override fun bindData(binding: ClassTodayBinding, item: Classes, position: Int) {
        val dateFormat = SimpleDateFormat("HH.mm", Locale.getDefault())
        binding.className.text = item.name
        binding.location.text = item.location
        binding.time.text = "${dateFormat.format(item.startTime)} - ${dateFormat.format(item.endTime)}"
    }

    override fun onItemClick(binding: ClassTodayBinding, item: Classes, position: Int) {

    }
}