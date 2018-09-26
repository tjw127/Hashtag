package co.uglytruth.hashtag.yahoo

import com.google.gson.annotations.SerializedName

/**
 * Created by tjw127 on 1/27/18.
 */
data class YahooWoeID(
        @SerializedName("query")
        val query: Query

)

data class Query(
        @SerializedName("count")
        val count:String,
        @SerializedName("created")
        val created:String,
        @SerializedName("lang")
        val lang:String,
        @SerializedName("results")
        val results: Results
)

data class Results(
     @SerializedName("place")
     val place:Place
)

data class Place(
        @SerializedName("woeid")
        val woeID: String
)