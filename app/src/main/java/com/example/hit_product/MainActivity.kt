package com.example.hit_product

import android.app.Dialog
import android.view.View
import androidx.navigation.Navigation.findNavController
import com.example.hit_product.base.BaseActivity
import com.example.hit_product.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){
    private val dialog by lazy { Dialog(this) }
    override fun initData() {

    }

    override fun bindData() {

    }

    override fun setOnClick() {

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.bottomBar)
        val destination = navController.currentDestination
        if (destination != null) {
            if (destination.id == R.id.loginFragment) {
                // Hide the bottom bar
                val bottomBar = findViewById<me.ibrahimsn.lib.SmoothBottomBar>(R.id.bottomBar)
                bottomBar.visibility = View.GONE
                return navController.navigateUp()
            }
        }
        return super.onSupportNavigateUp()
    }
}