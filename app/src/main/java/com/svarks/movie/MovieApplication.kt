package com.svarks.movie

import android.app.Application

private lateinit var application: MovieApplication

/**
 * Application class
 */
class MovieApplication : Application() {

    companion object {
        /**
         * @return application context
         */
        fun getApplicationContext(): MovieApplication {
            return application
        }
    }

    /**
     * Initializing the application context
     */
    override fun onCreate() {
        super.onCreate()
        application = this
    }
}
