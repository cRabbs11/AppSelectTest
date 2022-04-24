package com.ekochkov.appselecttest.data.entity

data class NYTFilm(
        val byline: String,
        val critics_pick: Int,
        val date_updated: String,
        val display_title: String,
        val headline: String,
        val link: NYTLink,
        val mpaa_rating: String,
        val multimedia: NYTMultimedia,
        val opening_date: String,
        val publication_date: String,
        val summary_short: String
)