package co.uglytruth.hashtag.yahoo

import android.content.Context
import co.uglytruth.hashtag.request.Request

/**
 * Created by tjw127 on 1/28/18.
 */

class YahooClient{

    fun getResponse(url:String, context: Context?)
    {
        var requestBuilder:Request.Builder = Request.Builder()

        requestBuilder.url(url)
        requestBuilder.doInput(true)
        requestBuilder.useCache(false)
        requestBuilder.requestMethod("GET")
        requestBuilder.context(context!!)

        val contentType = mapOf(Pair("Content-Type", "application/json"))


        requestBuilder.saveToSharedPreferenceName("YahooWoeID")
        requestBuilder.addProperty(contentType)

        requestBuilder.build()
    }
}