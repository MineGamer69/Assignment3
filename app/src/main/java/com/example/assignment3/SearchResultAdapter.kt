import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment3.MovieNew
import com.example.assignment3.R

class SearchResultAdapter(private var searchResults: List<com.example.assignment3.Result>) :
    RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_result, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val iconImageView: ImageView = view.findViewById(R.id.iconImageView)
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val platformTextView: TextView = view.findViewById(R.id.platformTextView)

        fun bind(searchResult: com.example.assignment3.Result) {
            Glide.with(itemView.context).load(searchResult.picture).into(iconImageView)
            nameTextView.text = searchResult.name
            platformTextView.text = searchResult.locations.joinToString { it.display_name }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchResult = searchResults[position]
        holder.bind(searchResult)
    }

    override fun getItemCount() = searchResults.size

    fun updateData(newData: List<com.example.assignment3.Result>) {
        searchResults = newData
        notifyDataSetChanged()
    }
}
