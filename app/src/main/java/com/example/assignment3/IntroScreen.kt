package com.example.assignment3

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson


class IntroScreen : Fragment(), View.OnClickListener {
    lateinit var navCtrl: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_intro_screen, container, false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navCtrl = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.start_button).setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        Toast.makeText(context, "Submitting...", Toast.LENGTH_SHORT).show()
        val movieName: EditText = requireView().findViewById(R.id.movieName)
        val countryCode: EditText = requireView().findViewById(R.id.countryCode)

        if (!TextUtils.isEmpty(movieName.text.toString())) {
            if (!TextUtils.isEmpty(countryCode.text.toString())) {
                val movNameRdy = movieName.text.toString()
                val countryCodeRdy = countryCode.text.toString()

                // Make the API call using the API interface
                val apiInterface = ApiInterface.create().getMoviebyname(movNameRdy, countryCodeRdy)

                apiInterface?.enqueue(object : Callback<ArrayList<MovieResult?>?> {
                    override fun onResponse(
                        call: Call<ArrayList<MovieResult?>?>,
                        response: Response<ArrayList<MovieResult?>?>
                    ) {
                        if (response?.body() != null) {
                            val gson = Gson()
                            val resultsJson = gson.toJson(response.body())
                            val action =
                                IntroScreenDirections.actionIntroScreenToResultFragment(resultsJson)
                            view?.findNavController()?.navigate(action)
                        } else {
                            Toast.makeText(
                                context,
                                "Error could not make the API call!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<MovieResult?>?>, t: Throwable) {
                        t.message?.let {
                            Log.d("onFailure", it)
                            Toast.makeText(
                                context,
                                "Error could not make the API call!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                })

            } else {
                Toast.makeText(context, "Please enter a country code!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Please enter a movie name!", Toast.LENGTH_SHORT).show()
        }
    }
}
