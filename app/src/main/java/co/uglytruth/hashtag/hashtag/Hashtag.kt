package co.uglytruth.hashtag.hashtag
import com.google.gson.annotations.SerializedName
/**
 * Created by tjw127 on 1/14/18.
 */

data class Hashtag(

        @SerializedName("hashtag_id")
        val hashtag_id: String,

        @SerializedName("hashtag_type")
        val hashtag_type: String,

        @SerializedName("hashtag_list")
        val hastag_list: String,

        @SerializedName("hashtag_timestamp")
        val hastag_timestamp: String

)