package com.example.movieslistapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.movieslistapp.R
import com.example.movieslistapp.adapter.MovieAdapter
import com.example.movieslistapp.databinding.ActivityMainBinding
import com.example.movieslistapp.databinding.FragmentLatestMovieListBinding
import com.example.movieslistapp.retrofit.MainRepository
import com.example.movieslistapp.retrofit.RetrofitService
import com.example.movieslistapp.viewModel.LatestMovieViewModel
import com.example.movieslistapp.viewModel.MyViewModelFactory

class LatestMovieList : Fragment() {
    lateinit var viewModel: LatestMovieViewModel
    private val adapter = MovieAdapter()
    private var _binding: FragmentLatestMovieListBinding? = null
    var pageNumber =1;
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLatestMovieListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofitService = RetrofitService.getInstance("https://api.themoviedb.org/3/")
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter
        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(LatestMovieViewModel::class.java)

        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.setMovies(it!!)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner!!) {
            Toast.makeText(context!!, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(viewLifecycleOwner!!, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
if(!binding.recyclerview.canScrollVertically(1)){
    viewModel.getAllLatestMovies(pageNumber++)
}
            }
        })

        viewModel.getAllLatestMovies(pageNumber)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}