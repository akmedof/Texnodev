package com.texnodevcom.texnodev.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.texnodevcom.texnodev.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        navController = findNavController(R.id.fragment)
        val appBarConfiguration : AppBarConfiguration = AppBarConfiguration(setOf(
            R.id.newsFragment,
            R.id.favoriteFragment,
            R.id.categoryFragment,
            R.id.settingFragment
        ))

        bottomBar.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

//        bottomBar.setOnNavigationItemSelectedListener(
//            { item ->
//                when (item.itemId) {
//                    R.id.newsFragment,
//                    R.id.categoryFragment,
//                    R.id.favoriteFragment,
//                    R.id.settingFragment -> {
//                    }
//                }
//                true
//            })

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}