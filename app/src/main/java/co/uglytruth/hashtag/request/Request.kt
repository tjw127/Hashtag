package co.uglytruth.hashtag.request

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.preference.PreferenceManager
import android.util.Log
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

/**
 * Created by tjw127 on 1/27/18.
 */
class Request{

    private  constructor(builder:Builder)
    class Builder{


        private var propertyList:ArrayList<Map<String, String?>> = ArrayList<Map<String, String?>>()
        private var requestMethod:String = ""
        private var doInput:Boolean = false
        private var useCaches:Boolean = false
        private var charset:String = ""
        private var url:String = ""
        private var context:Context? = null
        private var save:String? = null
        private var parameters:String? = null


        fun  addProperty(map: Map<String, String?>) = apply {

            propertyList.add(map)
        }

        fun requestMethod(value:String) = apply {

            requestMethod = value
        }

        fun doInput(value:Boolean) = apply {

            doInput = value
        }

        fun useCache(value:Boolean) = apply {
            useCaches = value
        }


        fun charsetName(value:String) = apply {

            charset = value
        }

        fun context(value:Context) = apply {

            context = value
        }

        fun url(value:String ) = apply {

            url = value
        }

        fun parameters(value: String) = apply {

            parameters = value
        }

        fun saveToSharedPreferenceName(value:String) = apply {

            save = value

        }

        fun build() : Request{

            try {

                URLBackgroundService().execute()

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return Request(this)

        }


         inner class URLBackgroundService : AsyncTask<String, String, String>() {

            override fun doInBackground(vararg params: String): String? {

                Log.v("Request", " url " + url)
                try {

                    val url = URL(url)

                    val httpURLConnection = url.openConnection() as HttpURLConnection

                    httpURLConnection.doInput = doInput
                    httpURLConnection.useCaches = useCaches


                    httpURLConnection.setRequestMethod(requestMethod)

                    for (property in propertyList)
                    {
                        Log.v("Request", " property " + property)
                        val key = property.keys.toTypedArray()[0]

                        httpURLConnection.addRequestProperty(key, property.get(key))
                    }

                    if (requestMethod == "POST")
                    {
                        if (parameters!!.isNotEmpty())
                        {
                            val postData = parameters!!.toByteArray(StandardCharsets.UTF_8)
                            val wr = DataOutputStream(httpURLConnection.outputStream)
                            wr.write(postData)
                            wr.close()
                        }
                    }

                    httpURLConnection.connect()

                    val inputStream = BufferedInputStream(httpURLConnection.inputStream)

                    val reader = BufferedReader(InputStreamReader(inputStream, "iso-8859-1") as Reader?)

                    var line: String? = null


                    val stringBuilder = StringBuilder()

                    for (j in reader.readLines())
                    {

                        stringBuilder.append(j + "\n")
                    }

                    Log.v("Request", " Results " + stringBuilder.toString())

                    if (save!!.isNotEmpty()) {

                        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString(save, stringBuilder.toString())
                        editor.apply()
                        editor.commit()




                    }

                    context = null


                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return null
            }
        }


    }
}