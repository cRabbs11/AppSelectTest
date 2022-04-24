package com.ekochkov.appselecttest.data.entity

data class NYTFilmData(
        val copyright: String,
        val has_more: Boolean,
        val num_results: Int,
        val results: List<NYTFilm>,
        val status: String
)