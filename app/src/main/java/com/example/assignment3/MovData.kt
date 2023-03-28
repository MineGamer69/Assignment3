package com.example.assignment3

data class MovData (
    val id: String,
    val picture: String?,
    val name: String,
    val country: String,
    val locations: List<MovieLocation>
)
data class MovieSearchResponse(
    val results: List<MovData>,
    val updated: String,
    val term: String
)
data class MovieLocation(
    val name: String,
    val url: String?,
    val icon: String?,
    val id: String?
)
