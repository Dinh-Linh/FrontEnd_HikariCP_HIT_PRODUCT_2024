package com.example.hit_product.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hit_product.R
import com.example.hit_product.base.BaseAdapter
import com.example.hit_product.base.BaseViewHolder
import com.example.hit_product.data.data_class.UserInformation
import com.example.hit_product.databinding.ClassInformationBinding


class UserInformationAdapter() :
    BaseAdapter<UserInformation, ClassInformationBinding>(ClassInformationBinding::inflate) {

    private val userInformation = mutableListOf<UserInformation>()


    override fun bindData(binding: ClassInformationBinding, item: UserInformation, position: Int) {

        binding.birth.text = item.birth
        binding.gen.text = item.gen
        binding.email.text = item.email
        binding.phone.text = item.phone
        binding.address.text = item.address
        binding.fullName.text = item.fullName
        binding.className.text = item.className

        if (item.avatar?.isEmpty() == true) {
            binding.avatar.setImageResource(R.drawable.default_avatar)
        } else {
            Glide.with(binding.avatar.context)
                .load("${item.avatar}")
                .into(binding.avatar)
        }


        if (item.qr?.isEmpty() == true){
            binding.qr.setImageResource(R.drawable.qr_zalo)
        }
        else{
            Glide.with(binding.qr.context)
                .load("${item.qr}").into(binding.qr)
        }

    }


    override fun onItemClick(
        binding: ClassInformationBinding,
        item: UserInformation,
        position: Int
    ) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ClassInformationBinding> {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ClassInformationBinding.inflate(inflate, parent, false)
        return BaseViewHolder(binding)
    }


}