package co.uglytruth.hashtag.yahoo.gson

import co.uglytruth.hashtag.yahoo.YahooWoeID
import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Created by tjw127 on 1/27/18.
 */

class YahooGson{

    fun parseData(json:String):YahooWoeID{

        val gson:Gson = GsonBuilder().create()

        val yahooWoeID =  gson.fromJson<YahooWoeID>(json, YahooWoeID::class.java)

        return yahooWoeID
    }
}