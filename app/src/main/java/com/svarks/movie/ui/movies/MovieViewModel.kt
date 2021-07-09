package com.svarks.movie.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.svarks.movie.datamodel.Result
import com.svarks.movie.ui.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @param repository
 * Movie list vieModel class
 */
open class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    val movieListLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val movieList: MutableLiveData<List<Result>> = MutableLiveData()

    fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            movieListLoading.postValue(true)
            try {
                val response = repository.getMovies()
                if (response.results.isNotEmpty()) {
                    movieList.postValue(response.results)
                }
                movieListLoading.postValue(false)
            } catch (e: Exception) {
                movieListLoading.postValue(false)
            }
        }
    }
}
