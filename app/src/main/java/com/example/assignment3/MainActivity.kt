package com.example.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiInterface = ApiInterface.create().getMoviebyname("skyfall","us")

        if (apiInterface != null) {
            apiInterface.enqueue( object : Callback<ArrayList<MovData?>?> {
                override fun onResponse(
                    call: Call<ArrayList<MovData?>?>?,
                    response: Response<ArrayList<MovData?>?>
                ) {
                    if (response != null) {
                        Log.d("Main activity", response.message())
                        Log.d("Main activity", response.headers().toString())
                        Log.d("Main activity", response.body().toString())
                    }

                }

                override fun onFailure(call: Call<ArrayList<MovData?>?>, t: Throwable) {
                    if (t != null) {
                        t.message?.let { Log.d("onFailure", it) }
                    }
                }
            })
        }

    }
}