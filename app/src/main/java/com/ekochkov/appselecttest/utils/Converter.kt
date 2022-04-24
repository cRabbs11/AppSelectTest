package com.ekochkov.appselecttest.utils

import com.ekochkov.appselecttest.Film
import com.ekochkov.appselecttest.data.entity.NYTFilm

object Converter {

    fun fromNYTFilmListToFilmList(filmData: List<NYTFilm>): List<Film> {
        val list = arrayListOf<Film>()
        filmData.forEach {
            list.add(Film(
                it.display_title,
                it.summary_short,
                it.multimedia.src))
        }
        return list
    }
}