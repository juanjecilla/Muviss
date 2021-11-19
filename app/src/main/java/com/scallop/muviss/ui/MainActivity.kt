package com.scallop.muviss.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.scallop.muviss.databinding.ActivityMainBinding
import com.scallop.muviss.ui.commons.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityMainBinding>()
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(binding.navHostFragment.id)
        return navController.navigateUp() ||
            super.onSupportNavigateUp()
    }

    private fun setUpNavigation() {
        with(binding) {
            this@MainActivity.navHostFragment = supportFragmentManager
                .findFragmentById(navHostFragment.id) as NavHostFragment

            setSupportActionBar(toolbar)
            setupActionBarWithNavController(this@MainActivity.navHostFragment.navController)
        }
    }
}
