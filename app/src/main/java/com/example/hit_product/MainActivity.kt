package com.example.hit_product

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hit_product.base.BaseActivity
import com.example.hit_product.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val dialog by lazy { Dialog(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bottomBar = binding.bottomBar
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavContainer) as NavHostFragment
        val navController = navHostFragment.navController



        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashScreenFragment, R.id.loginFragment, R.id.forgetFragment, R.id.timetableFragment, R.id.classRegistrationFragment -> {
                    binding.bottomBar.visibility = View.GONE
                }

                else -> {
                    binding.bottomBar.visibility = View.VISIBLE
                }
            }
        }
        binding.bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHome -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.navProfile -> {
                    navController.navigate(R.id.informationFragment)
                    true
                }
                R.id.navInf -> {
                    true
                }
                R.id.navSetting -> {
                    true
                }
                else -> false
            }
        }
    }

    override fun initData() {

    }

    override fun bindData() {

    }

    override fun setOnClick() {
        val bottomBar = binding.bottomBar
        bottomBar.visibility = View.GONE

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.bottomBar)
        val destination = navController.currentDestination
        if (destination != null) {
            if (destination.id == R.id.loginFragment) {
                // Hide the bottom bar
                val bottomBar = binding.bottomBar
                bottomBar.visibility = View.GONE
                return navController.navigateUp()
            }
        }
        return super.onSupportNavigateUp()
    }
}