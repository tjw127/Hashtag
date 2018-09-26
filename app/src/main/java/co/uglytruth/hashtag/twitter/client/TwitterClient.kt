package co.uglytruth.hashtag.twitter.client

import android.content.Context
import co.uglytruth.hashtag.request.Request
import co.uglytruth.hashtag.twitter.TwitterBearerToken

/**
 * Created by tjw127 on 1/28/18.
 */

class TwitterClient{

    fun getAccessToken(url:String, context: Context, parameters:String){

        var requestBuilder:Request.Builder = Request.Builder()

        requestBuilder.doInput(true)

        requestBuilder.useCache(false)

        val bearerToken:TwitterBearerToken = TwitterBearerToken()

        val token = bearerToken.getBearerToken()

        requestBuilder.url(url)

        requestBuilder.requestMethod("POST")

        requestBuilder.context(context)

        requestBuilder.parameters(parameters)

        requestBuilder.addProperty(mapOf("Authorization" to "Basic $token"))

        requestBuilder.addProperty(mapOf("Host" to "api.twitter.com"))

        requestBuilder.addProperty(mapOf("User-Agent" to "Swaglifehub"))

        requestBuilder.addProperty(mapOf("Content-Type" to "application/x-www-form-urlencoded;charset=UTF-8"))

        requestBuilder.addProperty(mapOf("Content-Type" to "29"))

        requestBuilder.saveToSharedPreferenceName("TwitterToken")

        requestBuilder.build()


    }

    fun getData(url: String, context: Context, token:String)
    {
        var requestBuilder:Request.Builder = Request.Builder()

        requestBuilder.doInput(true)

        requestBuilder.useCache(false)

        requestBuilder.requestMethod("GET")

        val tokenBearer = "Bearer " + token

        requestBuilder.addProperty(mapOf("Authorization" to tokenBearer))

        requestBuilder.addProperty(mapOf("Content-Type" to "application/json"))


        requestBuilder.saveToSharedPreferenceName("TwitterData")

        requestBuilder.build()
    }

}