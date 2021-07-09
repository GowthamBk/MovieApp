package com.svarks.movie.util

import androidx.lifecycle.ViewModelProvider
import com.svarks.movie.ui.movies.MovieViewModelFactory
import com.svarks.movie.ui.repository.MovieRepository

object Injection {
    private fun provideMovieRepository(): MovieRepository {
        return MovieRepository()
    }

    fun provideMovieViewModelFactory(): ViewModelProvider.Factory {
        return MovieViewModelFactory(provideMovieRepository())
    }
}
