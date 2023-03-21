package com.example.assignment3

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiInterface {

    @GET("/lookup")
    @Headers("X-RapidAPI-Key:", "X-RapidAPI-Host:utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com")
    fun getMoviebyname(@Query("term") name:String, @Query("country") country:String) : Call<ArrayList<MovData?>?>?

    @GET("/idlookup")
    @Headers("X-RapidAPI-Key:", "X-RapidAPI-Host:utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com")
    fun getMoviebyid(@Query("source_id") srcID:String, @Query("source") imdb:String) : Call<ArrayList<MovIDData?>?>?


    companion object {

        var BASE_URL = "https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com"


        fun create() : ApiInterface {

            val logging = HttpLoggingInterceptor()
// set your desired log level
// set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging) // <-- this is the important line!
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}