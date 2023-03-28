package com.example.assignment3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


var movieList: List<MovieResult> = ArrayList()

class RecyclerAdapter(val context: Context,  var navController: NavController) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MyViewHolder(view, context, navController)
    }



    override fun getItemCount(): Int {
        return movieList.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(position)
    }

    fun setMoviesListItems(movies: ArrayList<MovieResult>) {
        movieList = movies
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View, private val context: Context, private val navController: NavController) : RecyclerView.ViewHolder(itemView) {
       private val image: ImageView = itemView!!.findViewById(R.id.movie_image)
       private val title: TextView = itemView!!.findViewById(R.id.movie_title)
        private var pos:Int = 0
        //val streamingPlatforms: TextView = itemView.findViewById(R.id.locations)
        init {
            itemView.setOnClickListener {
//                val action = MovieResultsDirections.actionMainFragment2ToDetailFragment(pos)
                val action = ResultFragmentDirections.actionResultFragmentToDetailFragment(pos)
                navController.navigate(action)

            }
        }
        fun bind(position:Int){
            pos = position
            val currMovie = movieList[position]
            title.text = currMovie.name
            Glide.with(context).load(currMovie.picture)
                .apply(RequestOptions().centerCrop())
                .into(image)
        }

    }
}