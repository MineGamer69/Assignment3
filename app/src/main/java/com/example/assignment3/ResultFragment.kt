

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.R
import com.example.assignment3.RecyclerAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview)
//        recyclerAdapter = RecyclerAdapter(requireContext(), Navigation.findNavController(view))
        recyclerAdapter = RecyclerAdapter(requireContext(), navController)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter
        navController = Navigation.findNavController(view)

//        val apiInterface = ApiInterface.create().getMoviebyname()
//
//        apiInterface?.enqueue(object : Callback<ArrayList<MovieResult?>?> {
//            override fun onResponse(
//                call: Call<ArrayList<MovieResult?>?>,
//                response: Response<ArrayList<MovieResult?>?>
//            ) {
//
//
//                if (response?.body() != null)
//                    recyclerAdapter.setMoviesListItems(response.body()!! as ArrayList<MovieResult>)
//            }
//
//            override fun onFailure(call: Call<ArrayList<MovieResult?>?>, t: Throwable) {
//                if (t != null)
//                    t.message?.let {
//                        Log.d("onFailure", it)
//                    }
//            }
//
//        })


    }

}




