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

    private lateinit var searchResultRecyclerView: RecyclerView
    private lateinit var searchResultAdapter: SearchResultAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchResultRecyclerView = view.findViewById(R.id.searchResultRecyclerView)
        searchResultRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize the adapter
        searchResultAdapter = SearchResultAdapter(emptyList())
        searchResultRecyclerView.adapter = searchResultAdapter

        val bundle = arguments
        val mN = bundle?.let { SearchResultFragmentArgs.fromBundle(it).movName.toString() }
        val cou = bundle?.let { SearchResultFragmentArgs.fromBundle(it).country.toString() }

        if (mN != null && cou != null) {
            ApiInterface.create().getMoviebyname(mN, cou).enqueue(object : Callback<Movie.SearchResultResponse> {
                override fun onResponse(call: Call<Movie.SearchResultResponse>, response: Response<Movie.SearchResultResponse>) {
                    val searchResults = response.body()?.searchResults ?: emptyList()
                    // Update the adapter with the new data
                    searchResultAdapter.updateData(searchResults)
                }


                override fun onFailure(call: Call<Movie.SearchResultResponse>, t: Throwable) {
                    // Handle failure case
                    t.message?.let { Log.d("onFailure", it) }
                }
            })
        }
    }
}
