// Author: Aaryan Kapoor & Matt Nova
import com.google.gson.annotations.SerializedName

data class Movie(
    val id: String,
    val name: String,
    val locations: List<Location>,
    val provider: Provider,
    val weight: Int,
    val externalIds: ExternalIds,
    val picture: String
) {
    data class SearchResultResponse(
        @SerializedName("results") val searchResults: List<Movie>
    )

    data class Location(
        val display_name: String,
        val name: String,
        val id: String,
        val url: String,
        val icon: String
    )

    data class Provider(
        val display_name: String,
        val name: String,
        val id: String,
        val icon: String
    )

    data class ExternalIds(
        val imdb: Imdb?,
        val tmdb: Tmdb?,
        val iva: Iva?,
        val wikipedia_id: WikipediaId?
    )

    data class Imdb(
        val url: String,
        val id: String
    )

    data class Tmdb(
        val url: String,
        val id: String
    )

    data class Iva(
        val id: String
    )

    data class WikipediaId(
        val url: String,
        val id: String
    )
}
