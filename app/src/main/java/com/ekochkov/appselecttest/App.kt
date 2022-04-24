package com.ekochkov.appselecttest

import android.app.Application
import com.ekochkov.appselecttest.utils.NYTApi
import com.ekochkov.appselecttest.utils.NYT_API_CONSTANTS
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    lateinit var retrofitService: NYTApi

    override fun onCreate() {
        super.onCreate()
        instance = this

        val retrofit = Retrofit.Builder()
            .baseUrl(NYT_API_CONSTANTS.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitService = retrofit.create(NYTApi::class.java)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}