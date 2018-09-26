package co.uglytruth.hashtag.twitter.rest.gson

import co.uglytruth.hashtag.twitter.TwitterStatuses
import co.uglytruth.hashtag.twitter.rest.TWTweets
import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Created by tjw127 on 1/27/18.
 */
class TWTweetsGson{

    fun parseData(json:String):TwitterStatuses{

        val gson:Gson = GsonBuilder().create()

        val statuses = gson.fromJson<TwitterStatuses>(json, TwitterStatuses::class.java) as TwitterStatuses

        return statuses
    }
}