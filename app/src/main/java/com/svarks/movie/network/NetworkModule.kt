package com.svarks.movie.network

import com.svarks.movie.util.AppConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/reviews/"

    /**
     * @return OkHttpClient
     */
    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(AppConstant.CONNECTION_TIMEOUT.toLong(), TimeUnit.MINUTES)
            .readTimeout(AppConstant.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }

    /**
     * @return Retrofit builder
     */
    fun getClient(): NetworkApi {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkApi::class.java)
    }
}
