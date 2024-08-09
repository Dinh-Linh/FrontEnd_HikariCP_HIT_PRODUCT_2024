package com.example.hit_product

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hit_product.base.BaseActivity
import com.example.hit_product.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavContainer) as NavHostFragment
        val navController = navHostFragment.navController
        val homeDestinationId = R.id.homeFragment


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashScreenFragment, R.id.loginFragment, R.id.emailFragment, R.id.OTPFragment, R.id.newPasswordFragment, R.id.timetableFragment, R.id.classInformationFragment, R.id.classRegistrationFragment, R.id.generalNotificationFragment, R.id.accountFragment-> {
                    binding.bottomBar.visibility = View.GONE
                }

                else -> {
                    binding.bottomBar.visibility = View.VISIBLE
                    binding.bottomBar.setSelectedWithId(destination.id, false)
                }
            }
        }
        binding.bottomBar.setSelectedWithId(navController.currentDestination?.id ?: homeDestinationId, false)
        binding.bottomBar.addBubbleListener { item ->
            when (item) {
                R.id.navHome -> {
                    navController.navigate(R.id.homeFragment)
                }

                R.id.navProfile -> {
                    navController.navigate(R.id.informationFragment)
                }

                R.id.navInf -> {
                    navController.navigate(R.id.introductionFragment)
                }

                R.id.navSetting -> {
                    navController.navigate(R.id.settingFragment)
                }


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