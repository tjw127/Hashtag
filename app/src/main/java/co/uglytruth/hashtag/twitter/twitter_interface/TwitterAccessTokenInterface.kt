package co.uglytruth.hashtag.twitter.twitter_interface

import co.uglytruth.hashtag.twitter.TwitterAccessToken
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by tjw127 on 1/24/18.
 */
interface TwitterAccessTokenInterface{

    @Headers(

        "Content-Type: application/x-www-form-urlencoded; charset=UTF-8",
        "User-Agent: Swaglifehub",
        "Content-Length: 29",
        "Accept-Encoding: gzip"

    )
    @POST("oauth2/token")
    fun getTwitterApplicationOnlyAccessToken(@Header("Authorization") authorization: String, @Query("grant_type") grant_type:String) : Call<TwitterAccessToken>

}