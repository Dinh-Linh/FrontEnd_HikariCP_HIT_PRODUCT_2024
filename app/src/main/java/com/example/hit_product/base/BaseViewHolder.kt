package com.example.hit_product.base

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding


class BaseViewHolder<VB : ViewBinding>(val binding: VB) : ViewHolder(binding.root) {
}