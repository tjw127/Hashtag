package co.uglytruth.hashtag

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.preference.PreferenceManager
import android.util.Log
import co.uglytruth.hashtag.twitter.TwitterBearerToken
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

/**
 * Created by tjw127 on 1/25/18.
 */
class Webservice{

    private var urlString: String? = null

    private var context: Context? = null

    private var enums: String? = null

    private val result: String? = null

    private var parameters: String? = null

    @Throws(Exception::class)
    fun getResponse(urlString: String, context: Context, enums: String?, parameter: String): String? {

        this.enums = enums

        this.urlString = urlString

        this.context = context

        this.parameters = parameter

        URLBackgroundService().execute()

        return this.urlString
    }

    fun getYahoo(urlString: String?) {

        try {

            val urlStringFinal = urlString

            val url = URL(urlStringFinal)

            val httpURLConnection = url.openConnection() as HttpURLConnection

            httpURLConnection.doInput = true
            httpURLConnection.useCaches = false

            httpURLConnection.setDoInput(true)

            httpURLConnection.setRequestMethod("GET")


            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            httpURLConnection.connect()

            val inputStream = BufferedInputStream(httpURLConnection.inputStream)

            val reader = BufferedReader(InputStreamReader(inputStream, "iso-8859-1") as Reader?)
            var line: String? = null


            val stringBuilder = StringBuilder()

            for (j in reader.readLines())
            {
                Log.v("YahooData", j)
                stringBuilder.append(j + "\n")
            }

            Log.v("YahooURLView ", " " + stringBuilder.toString())
            val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("YahooWoeID", stringBuilder.toString())
            editor.apply()
            editor.commit()


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getData(urlString: String, requestMethod: String, parameters: String) {


        Log.v("URLView ", " " + urlString)
        val url: URL? = null
        try {


            val charset = "UTF-8"
            //String query = String.format("v=%s&session_id=%s&q=%s", URLEncoder.encode(v, charset),URLEncoder.encode("1234view",charset), URLEncoder.encode(textString, charset));

            val urlStringFinal = urlString
            val urlV = URL(urlStringFinal)
            val httpURLConnection = urlV.openConnection() as HttpURLConnection
            httpURLConnection.doInput = true
            httpURLConnection.useCaches = false

//            val bearerToken:TwitterBearerToken = TwitterBearerToken()
            val token = "AAAAAAAAAAAAAAAAAAAAAFBW0QAAAAAA1okAntAYq8UdQ3kd7TDC9EiJ7g8%3DI8cGsqxanOjQadw3Szq4eaeXoDpdnE35pgviZBUH0Gwli3nAHv"

            httpURLConnection.setDoInput(true)

            httpURLConnection.setRequestMethod("GET")


            httpURLConnection.setRequestProperty("Authorization", "Bearer " + token)

            httpURLConnection.setRequestProperty("Content-Type", "application/json");


//            httpURLConnection.setRequestProperty("Content-Length", "29");

            httpURLConnection.setUseCaches(false);


//            if (parameters.length > 0) {
//                val postData = parameters.toByteArray(StandardCharsets.UTF_8)
//                val postDataLength = postData.size
//////                httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength))
//                val wr = DataOutputStream(httpURLConnection.outputStream)
//                wr.write(postData)
//                wr.close()
//            }

            httpURLConnection.connect()

            val inputStream = BufferedInputStream(httpURLConnection.inputStream)

            val reader = BufferedReader(InputStreamReader(inputStream, "iso-8859-1") as Reader?)
            var line: String? = null


            val stringBuilder = StringBuilder()

            for (j in reader.readLines())
            {
                Log.v("TwitterData", j)
                stringBuilder.append(j + "\n")
            }

            Log.v("TwitterURLView ", " " + stringBuilder.toString())
            val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("TwitterJSON", stringBuilder.toString())
            editor.apply()
            editor.commit()


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun insertData(urlString: String, requestMethod: String, parameters: String) {


        Log.v("URLView ", " " + urlString)
        val url: URL? = null
        try {


            val charset = "UTF-8"
            //String query = String.format("v=%s&session_id=%s&q=%s", URLEncoder.encode(v, charset),URLEncoder.encode("1234view",charset), URLEncoder.encode(textString, charset));

            val urlStringFinal = urlString + "oauth2/token"
            val urlV = URL(urlStringFinal)
            val httpURLConnection = urlV.openConnection() as HttpURLConnection
            httpURLConnection.doInput = true
            httpURLConnection.useCaches = false

            val bearerToken:TwitterBearerToken = TwitterBearerToken()
            val token = bearerToken.getBearerToken()

            httpURLConnection.setDoInput(true)

            httpURLConnection.setRequestMethod("POST")

            httpURLConnection.setRequestProperty("Host", "api.twitter.com")

            httpURLConnection.setRequestProperty("User-Agent", "Swaglifehub")

            httpURLConnection.setRequestProperty("Authorization", "Basic " + token)

            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            httpURLConnection.setRequestProperty("Content-Length", "29");

            httpURLConnection.setUseCaches(false);
            //httpURLConnection.setRequestProperty("Content-Type", "application/json");
            //httpURLConnection.setRequestProperty("Accept", "application/json");
//            httpURLConnection.requestMethod = requestMethod


            //httpURLConnection.setRequestProperty("q", "how%20many%20bae%20between%20Tuesday%20and%20Friday");

           if (parameters.length > 0) {
                val postData = parameters.toByteArray(StandardCharsets.UTF_8)
                val postDataLength = postData.size
////                httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength))
                val wr = DataOutputStream(httpURLConnection.outputStream)
               wr.write(postData)
                wr.close()
            }

            httpURLConnection.connect()

            val inputStream = BufferedInputStream(httpURLConnection.inputStream)

            val reader = BufferedReader(InputStreamReader(inputStream, "iso-8859-1") as Reader?)
            var line: String? = ""

            val stringBuilder = StringBuilder()
            Log.v("TwitterURLView ", " " + reader.readLines()[0])
            val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("TwitterJSON", reader.readLines()[0])
            editor.apply()
            editor.commit()


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    inner class URLBackgroundService : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg params: String): String? {

            try {

//                insertData(urlString=urlString!!, requestMethod = "POST", parameters = "grant_type=client_credentials")

                    getYahoo(urlString = urlString)
//                getData(urlString = urlString!!, requestMethod = "GET", parameters = parameters!!)


//                val url = URL(urlString)
//
//                val reader = BufferedReader(InputStreamReader(url.openStream()))
//
//                var line: String? = ""
//
//                val stringBuilder = StringBuilder()
//                while (line in reader.readLines()) {
//
//                    Log.v("TwitterWebservice", line)
//
//                    stringBuilder.append(line + "\n")
//                }
//
//                val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//
//                val editor = sharedPreferences.edit()
//
//                editor.putString(enums, stringBuilder.toString())
//
//                editor.commit()
//
//
//                Log.v("Shopify V2", " " + stringBuilder.toString())


            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }
    }
}