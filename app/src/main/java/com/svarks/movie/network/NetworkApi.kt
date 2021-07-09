package com.svarks.movie.network

import com.svarks.movie.datamodel.IMovie
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Network service class
 */
interface NetworkApi {
    @GET("search.json")
    suspend fun getMovieList(@Query("api-key") apiKey: String): IMovie
}
