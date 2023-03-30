package com.example.assignment3

data class MovieNew(
    val results: List<Result>,
    val status_code: Int,
    val term: String,
    val updated: String,
    val variant: String
)

data class Result(
    val external_ids: ExternalIds,
    val id: String,
    val locations: List<Location>,
    val name: String,
    val picture: String,
    val provider: String,
    val weight: Int
)
data class Location(
    val display_name: String,
    val icon: String,
    val id: String,
    val name: String,
    val url: String
)
data class Tmdb(
    val id: String,
    val url: String
)

data class WikiData(
    val id: String,
    val url: String
)