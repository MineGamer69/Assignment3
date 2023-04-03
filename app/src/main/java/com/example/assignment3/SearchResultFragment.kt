// Author: Aaryan Kapoor & Matt Nova
package com.example.assignment3

import SearchResultAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultFragment : Fragment() {
    //setup lateinit variables

    private lateinit var searchResultRecyclerView: RecyclerView
    private lateinit var searchResultAdapter: SearchResultAdapter
    //sets up our createview
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //inflates our view
        return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //sets up our layout with recycle view

        searchResultRecyclerView = view.findViewById(R.id.searchResultRecyclerView)
        searchResultRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize the adapter
        searchResultAdapter = SearchResultAdapter(emptyList())
        searchResultRecyclerView.adapter = searchResultAdapter
        //setup bundle from previous inputs and sets it to our variable
        val bundle = arguments
        val mN = bundle?.let { SearchResultFragmentArgs.fromBundle(it).movName.toString() }
        val cou = bundle?.let { SearchResultFragmentArgs.fromBundle(it).country.toString() }

        if (mN != null && cou != null) {
            //makes the api call
            ApiInterface.create().getMoviebyname(mN, cou).enqueue(object : Callback<MovieNew> {
                override fun onResponse(call: Call<MovieNew>, response: Response<MovieNew>) {
                    val searchResults = response.body()?.results ?: emptyList()
                    Log.d("works","working")
                    // Update the adapter with the new data
                    searchResultAdapter.updateData(searchResults)
                }

//handling the on failure
                override fun onFailure(call: Call<MovieNew>, t: Throwable) {
                    // Handle failure case
                    t.message?.let { Log.d("onFailure", it) }
                }
            })
        }
    }

}
