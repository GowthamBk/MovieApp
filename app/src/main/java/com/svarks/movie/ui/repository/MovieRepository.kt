package com.svarks.movie.ui.repository

import com.svarks.movie.datamodel.IMovie
import com.svarks.movie.network.NetworkModule

open class MovieRepository {
    suspend fun getMovies(): IMovie = NetworkModule.getClient().getMovieList("fnZBgyIOf8KKm6zVO8Lq91xnLLglOp7t")
}
