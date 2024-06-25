package com.example.hit_product.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavInflater
import androidx.viewbinding.ViewBinding
import com.example.hit_product.utils.extension.showLoading

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> VB
) : Fragment(){
    private var _binding : VB? = null
    protected val binding: VB
        get() = _binding as VB

    protected abstract val viewModel : BaseViewModel

    protected val loadingDialog by lazy { context?.let { Dialog(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loading.observe(viewLifecycleOwner){
            if(it){
                if(loadingDialog?.isShowing == false){
                    loadingDialog?.showLoading()
                }
            } else{
                loadingDialog?.dismiss()
            }
        }
        bindData()
        observeData()
        setOnClick()
    }
    abstract fun initData()

    abstract fun bindData()

    abstract fun observeData()

    abstract fun setOnClick()
}