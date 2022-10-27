package com.bignerdranch.android.a20221024_mysiaelliott_nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.databinding.ActivityMainBinding
import com.bignerdranch.android.a20221024_mysiaelliott_nycschools.view.ScoresFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = supportFragmentManager.findFragmentById(R.id.nav_container_frag) as NavHostFragment

        setupActionBarWithNavController(navController.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_container_frag).navigateUp()
    }
}