package com.ekochkov.appselecttest

import android.app.Application
import com.ekochkov.appselecttest.utils.NYTApi
import com.ekochkov.appselecttest.utils.NYT_API_CONSTANTS
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    lateinit var retrofitService: NYTApi

    override fun onCreate() {
        super.onCreate()
        instance = this

        val client = OkHttpClient.Builder().apply {
            interceptors().add(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request()
                    val originalHttpUrl = original.url()

                    val url = originalHttpUrl.newBuilder()
                            .addQueryParameter(NYT_API_CONSTANTS.PARAMETER_NAME_API_KEY, BuildConfig.NYTApiKey)
                            .build()

                    val request = original.newBuilder()
                            .url(url)
                            .build()
                    return chain.proceed(request)
                }
            })
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(NYT_API_CONSTANTS.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitService = retrofit.create(NYTApi::class.java)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}