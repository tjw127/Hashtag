package co.uglytruth.hashtag.yahoo

import android.content.Context
import co.uglytruth.hashtag.Webservice
import java.net.URLEncoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * Created by tjw127 on 1/27/18.
 */

class Yahoo{

    //https://query.yahooapis.com/v1/public/yql?q=select%20woeid%20from%20geo.places%20where%20text%3D%22Atlanta%20%20ga%22%20limit%201&diagnostics=false&format=json

    fun getLocation(location:String, context:Context){

        val locationEncoded = URLEncoder.encode(location, StandardCharsets.UTF_8.toString())

        val url = "https://query.yahooapis.com/v1/public/yql?q=select%20woeid%20from%20geo.places%20where%20text%3D%22" + locationEncoded + "%22%20limit%201&diagnostics=false&format=json"

        val yahooClient:YahooClient = YahooClient()
        yahooClient.getResponse(url = url, context = context)

    }

}