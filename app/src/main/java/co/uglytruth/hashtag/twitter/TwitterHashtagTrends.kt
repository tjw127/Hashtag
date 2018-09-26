package co.uglytruth.hashtag.twitter

import com.google.gson.annotations.SerializedName

/**
 * Created by tjw127 on 1/27/18.
 */
data class TwitterHashtagTrends(

        @SerializedName("trends")
        val trends: ArrayList<Trends>

)

data class Trends(
        @SerializedName("name")
        val name:String,

        @SerializedName("url")
        val url:String,


        @SerializedName("promoted_content")
        val promoted_content:String,

        @SerializedName("query")
        val query:String,

        @SerializedName("tweet_volume")
        val tweet_volume:String
)