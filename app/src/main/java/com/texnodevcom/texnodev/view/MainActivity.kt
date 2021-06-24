package com.texnodevcom.texnodev.view

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.util.ConnectionLiveData
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var cld : ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.toolBarBackground)))

//        checkNetworkConnection()

        navController = findNavController(R.id.fragment)
        val appBarConfiguration : AppBarConfiguration = AppBarConfiguration(setOf(
            R.id.newsFragment,
            R.id.favoriteFragment,
            R.id.categoryFragment,
        ))

        bottomBar.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.postDetailsFragment -> bottomBar.visibility = View.GONE
                R.id.accountFragment -> bottomBar.visibility = View.GONE
                R.id.privacyFragment -> bottomBar.visibility = View.GONE
                R.id.categoryDetailsFragment -> bottomBar.visibility = View.GONE
                else -> bottomBar.visibility = View.VISIBLE
            }
        }


    }

    private fun checkNetworkConnection(){
        cld = ConnectionLiveData(application)
        cld.observe(this, Observer { isConnected ->
            if (isConnected){
                mainLayout.visibility = View.VISIBLE
                mainLayoutWifi.visibility = View.GONE
            }else{
                mainLayout.visibility = View.GONE
                mainLayoutWifi.visibility = View.VISIBLE
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}