package com.svarks.movie.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.svarks.movie.R
import com.svarks.movie.databinding.FragmentMoviesBinding
import com.svarks.movie.datamodel.Result
import com.svarks.movie.util.AppConstant
import com.svarks.movie.util.Injection
import com.svarks.movie.util.hide
import com.svarks.movie.util.show

/**
 * MoviesFragment to display the list of movie
 */
class MoviesFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onStart() {
        super.onStart()
        getMovieList()
    }

    /**
     * Initializing the viewModel
     */
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, Injection.provideMovieViewModelFactory()).get(MovieViewModel::class.java)
    }

    /**
     * hitting api to get the movies
     */
    private fun getMovieList() {
        viewModel.movieListLoading.observe(this) { checkAndShowLoader() }
        viewModel.getMovieList()
        viewModel.movieList.observe(this) { getMovieData() }
    }

    /**
     * To display progress bar
     */
    private fun checkAndShowLoader() {
        val movieListLoading = viewModel.movieListLoading.value!!
        val movieList = viewModel.movieList.value
        val loading = (movieListLoading && movieList == null)
        if (loading) {
            binding.progressBar.show()
        } else {
            binding.progressBar.hide()
        }
    }

    /**
     * Successful movie response
     */
    private fun getMovieData() {
        val movieList = viewModel.movieList.value!!
        initAdapter(movieList)
    }

    /**
     * @param movieList
     * Initializing adapter, passing the data and listener to navigate to the MovieDetailsFragment
     */
    private fun initAdapter(movieList: List<Result>) {
        movieAdapter = MovieAdapter(
            movieList,
            object : MovieAdapter.OnItemClickListener {
                override fun onItemClick(data: Result) {
                    val bundle = Bundle().apply {
                        putString(AppConstant.BYLINE, data.byline)
                        putString(AppConstant.HEADLINE, data.headline)
                        putString(AppConstant.SHORT_SUMMARY, data.summaryShort)
                        putString(AppConstant.PUBLICATION_DATE, data.publicationDate)
                        putString(AppConstant.OPENING_DATE, data.openingDate)
                    }
                    findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment, bundle)
                }
            }
        )
        binding.rvMovieList.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
    }
}
