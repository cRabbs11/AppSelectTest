package com.ekochkov.appselecttest.utils

import com.ekochkov.appselecttest.data.entity.NYTFilmData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NYTApi {

    @GET("movies/v2/reviews/{type}.json")
    fun getFilms(
        @Path("type") type: String,
        @Query("offset") offset: Int,
        @Query("api-key") api_key: String
    ): Call<NYTFilmData>
}