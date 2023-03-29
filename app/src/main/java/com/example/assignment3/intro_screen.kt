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
                val action = intro_screenDirections.actionIntroScreenToSearchResultFragment(movNameRdy, countryCodeRdy)
                navCtrl.navigate(action)

            }
        }
    }
}