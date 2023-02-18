package com.example.movieslistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieslistapp.adapter.MovieAdapter
import com.example.movieslistapp.databinding.ActivityMainBinding
import com.example.movieslistapp.retrofit.MainRepository
import com.example.movieslistapp.retrofit.RetrofitService
import com.example.movieslistapp.viewModel.LatestMovieViewModel
import com.example.movieslistapp.viewModel.MyViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = this.findNavController(R.id.nav_host_fragment)
        val navView: BottomNavigationView = findViewById(R.id.bottomNav)

        navView.setupWithNavController(navController)
    }
}