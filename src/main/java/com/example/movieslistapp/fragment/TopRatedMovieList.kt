package com.example.movieslistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.movieslistapp.adapter.MovieAdapter
import com.example.movieslistapp.databinding.FragmentMovieListBinding
import com.example.movieslistapp.retrofit.MainRepository
import com.example.movieslistapp.retrofit.RetrofitService
import com.example.movieslistapp.viewModel.MyViewModelFactory
import com.example.movieslistapp.viewModel.TopRatedMovieViewModel

class MovieListFragment : Fragment() {

    lateinit var viewModel: TopRatedMovieViewModel
    private val adapter = MovieAdapter()
    private var _binding: FragmentMovieListBinding? = null
    var pageNumber =1;
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofitService = RetrofitService.getInstance("https://api.themoviedb.org/3/")
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter
        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(
            TopRatedMovieViewModel::class.java)

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
                    viewModel.getAllTopRatedMovies(pageNumber++)
                }
            }
        })

        viewModel.getAllTopRatedMovies(pageNumber)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}