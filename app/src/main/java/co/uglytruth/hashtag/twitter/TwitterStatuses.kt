package co.uglytruth.hashtag.twitter

import com.google.gson.annotations.SerializedName

/**
 * Created by tjw127 on 1/27/18.
 */

data class TwitterStatuses(

    @SerializedName("statuses")
    val statuses:ArrayList<Statuses>

)
data class Statuses(
        @SerializedName("coordinates")
        val coordinates:String,

        @SerializedName("favorited")
        val favorited:String,

        @SerializedName("truncated")
        val truncated:String,

        @SerializedName("created_at")
        val created_at:String,

        @SerializedName("id_str")
        val id_str:String,

        @SerializedName("retweet_count")
        val retweet_count:String,

        @SerializedName("entities")
        val entities: Entities,

        @SerializedName("in_reply_to_user_id_str")
        val in_reply_to_user_id_str:String?,

        @SerializedName("text")
        val text:String,

        @SerializedName("id")
        val id:String,

        @SerializedName("geo")
        val geo:String?,

        @SerializedName("retweeted")
        val retweeted:String,

        @SerializedName("place")
        val place:String?,

        @SerializedName("user")
        val user:User
)
data class User(
        @SerializedName("profile_sidebar_fill_color")
        val profile_sidebar_fill_color:String,

        @SerializedName("profile_sidebar_border_color")
        val profile_sidebar_border_color:String,

        @SerializedName("profile_background_tile")
        val profile_background_tile:String,

        @SerializedName("name")
        val name:String,

        @SerializedName("profile_image_url")
        val profile_image_url:String,

        @SerializedName("created_at")
        val created_at:String,

        @SerializedName("location")
        val location:String,

        @SerializedName("follow_request_sent")
        val follow_request_sent:String,

        @SerializedName("profile_link_color")
        val profile_link_color:String,

        @SerializedName("is_translator")
        val is_translator:String,

        @SerializedName("id_str")
        val id_str: String,

        @SerializedName("followers_count")
        val followers_count:String,

        @SerializedName("statuses_count")
        val statuses_count:String,

        @SerializedName("friends_count")
        val friends_count:String,

        @SerializedName("following")
        val following:String,

        @SerializedName("screen_name")
        val screen_name:String


)
data class Entities(
//        @SerializedName("urls")
//        val urls:ArrayList<String>,

        @SerializedName("hashtags")
        val hashtags: ArrayList<Hashtags>,

        @SerializedName("user_mentions")
        val user_mentions: ArrayList<User_Mentions>


)

data class User_Mentions(
        @SerializedName("name")
        val name: String,

        @SerializedName("screen_name")
        val screen_name: String,

        @SerializedName("id")
        val id: String,

        @SerializedName("id_str")
        val id_str: String
)

data class Hashtags(

        @SerializedName("text")
        val text:String,

        @SerializedName("indices")
        val indices:ArrayList<String>
)