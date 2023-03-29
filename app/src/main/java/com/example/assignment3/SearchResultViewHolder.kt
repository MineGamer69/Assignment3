package com.example.assignment3

import Movie
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
    private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    private val platformTextView: TextView = itemView.findViewById(R.id.platformTextView)

    fun bind(movie: Movie) {
        Glide.with(itemView.context).load(movie.picture).into(iconImageView)
        nameTextView.text = movie.name
        platformTextView.text = movie.locations.joinToString { it.display_name }
    }
}
