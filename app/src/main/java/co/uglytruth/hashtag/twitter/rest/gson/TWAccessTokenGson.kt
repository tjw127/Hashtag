package co.uglytruth.hashtag.twitter.rest.gson

import co.uglytruth.hashtag.twitter.TwitterAccessToken
import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Created by tjw127 on 1/29/18.
 */
class TWAccessTokenGson{

    fun parseData(json:String):TwitterAccessToken{

        val gson: Gson = GsonBuilder().create()

        val statuses = gson.fromJson<TwitterAccessToken>(json, TwitterAccessToken::class.java) as TwitterAccessToken

        return statuses
    }
}