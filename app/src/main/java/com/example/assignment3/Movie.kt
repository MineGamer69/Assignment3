package com.example.assignment3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class MovieSearchResponse(
    val results: List<MovieResult>,
    val updated: String,
    val term: String
)

data class MovieResult(
    val id: String,
    val picture: String?,
    val name: String,
    val locations: List<MovieLocation>
)

data class MovieLocation(
    val name: String,
    val url: String?,
    val icon: String?,
    val id: String?
)

// missing more parameters
