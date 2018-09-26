package co.uglytruth.hashtag.twitter

import com.google.gson.annotations.SerializedName

/**
 * Created by tjw127 on 1/27/18.
 */
data class TwitterTrendsAvailable(

        @SerializedName("country")
        val country:String,
        @SerializedName("countryCode")
        val countryCode:String,
        @SerializedName("name")
        val name:String,
        @SerializedName("parentid")
        val parentid:String,
        @SerializedName("url")
        val url:String,
        @SerializedName("woeid")
        val woeid:String,
        @SerializedName("placeType")
        val placeType: PlaceType


)

data class PlaceType(
        @SerializedName("code")
        val code:String,
        @SerializedName("name")
        val name:String
)