package co.uglytruth.hashtag.twitter

import com.google.gson.annotations.SerializedName

/**
 * Created by tjw127 on 1/24/18.
 */

data class TwitterAccessToken(
        @SerializedName("token_type")
        val token_type:String,
        @SerializedName("access_token")
        val access_token: String
)