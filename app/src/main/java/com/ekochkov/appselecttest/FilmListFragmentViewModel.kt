package com.ekochkov.appselecttest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ekochkov.appselecttest.data.entity.NYTFilmData
import com.ekochkov.appselecttest.utils.API_KEYS
import com.ekochkov.appselecttest.utils.Converter
import com.ekochkov.appselecttest.utils.NYTApi
import com.ekochkov.appselecttest.utils.NYT_API_CONSTANTS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmListFragmentViewModel(): ViewModel() {

    val filmListLiveData = MutableLiveData<List<Film>>()
    private val filmList = ArrayList<Film>()
    private val retrofitService: NYTApi = App.instance.retrofitService
    private var NYTFilmsPage = 1
    private var isWaitingRequest = false
    private val INVISIBLE_FILMS_UNTIL_NEW_REQUEST = 2

    init {
        getFilms()
    }

    private fun getFilms() {
        isWaitingRequest = true
        retrofitService.getFilms(
            type = NYT_API_CONSTANTS.FILM_REVIEWS_TYPE_ALL,
            offset = NYTFilmsPage*NYT_API_CONSTANTS.FILMS_LIST_PAGE_KOEF
        ).enqueue(object: Callback<NYTFilmData> {
            override fun onResponse(call: Call<NYTFilmData>, response: Response<NYTFilmData>) {
                if (response.body()!=null) {
                    val anotherlist = Converter.fromNYTFilmListToFilmList(response.body()!!.results)
                    filmList.addAll(anotherlist)
                    filmListLiveData.postValue(filmList)
                    NYTFilmsPage++
                }
                isWaitingRequest = false
            }

            override fun onFailure(call: Call<NYTFilmData>, t: Throwable) {
                isWaitingRequest = false
            }

        })
    }

    fun getLastVisibleFilmInList(lastVisible: Int) {
        if (!isWaitingRequest) {
            val filmListSize = filmListLiveData.value?.size ?: 0
            if ((filmListSize - INVISIBLE_FILMS_UNTIL_NEW_REQUEST) <= lastVisible) {
                getFilms()
            }
        }
    }

}