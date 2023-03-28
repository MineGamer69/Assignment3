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
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class intro_screen : Fragment(), View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_intro_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navCtrl = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.start_button).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        Toast.makeText(context,"Submitting...", Toast.LENGTH_SHORT).show()
        val movieName: EditText = requireView().findViewById(R.id.movieName)
        val countryCode: EditText = requireView().findViewById(R.id.countryCode)
        if(!TextUtils.isEmpty(movieName.text.toString())){
            if(!TextUtils.isEmpty(countryCode.text.toString())){
                val movNameRdy = movieName.text.toString()
                val countryCodeRdy = countryCode.text.toString()
                val apiInterface = ApiInterface.create().getMoviebyname(movNameRdy,countryCodeRdy)

                apiInterface?.enqueue( object : Callback<ArrayList<MovData?>?> {
                    override fun onResponse(
                        call: Call<ArrayList<MovData?>?>?,
                        response: Response<ArrayList<MovData?>?>
                    ) {
                        Toast.makeText(context,"API Call Successful!", Toast.LENGTH_SHORT).show()
                        if (response?.body() != null) {
                            val gson = Gson()
                            val resultsJson = gson.toJson(response.body())
                            Log.d("Main activity", resultsJson.toString())
                            val action = intro_screenDirections.actionIntroScreenToResultFragment()

                            //val action = IntroScreenDirections.actionIntroScreenToResultFragment()
                            view?.findNavController()?.navigate(action)
                            //val action = intro_screenDirections.actionIntroScreenToResultFragment(resultsJson)
                            //view?.findNavController()?.navigate(action)
                        } else {
                            Toast.makeText(
                                context,
                                "Error could not make the API call!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        //if (response != null) {
                        //    Log.d("Main activity", response.message())
                        //Log.d("Main activity", response.headers().toString())
                        //    Log.d("Main activity", response.body().toString())
                        //    //change this below when matt is done
                            //val doAction = intro_screenDirections.actionIntroScreenToMainActivity()
                       //     val doAction = intro_screenDirections.actionIntroScreenToResultFragment()
                       //     p0?.findNavController()?.navigate(doAction)
                        }


                    override fun onFailure(call: Call<ArrayList<MovData?>?>?, t: Throwable) {
                        if (t != null) {
                            t.message?.let {
                                Log.d("onFailure", it)
                                Toast.makeText(context,"Error could not make the API call!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })

            }
        }
    }
}