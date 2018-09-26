package co.uglytruth.hashtag.twitter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import co.uglytruth.hashtag.Webservice
import co.uglytruth.hashtag.adapter.HashtagAdapter
import co.uglytruth.hashtag.enum.CredentialsEnum
import co.uglytruth.hashtag.hashtag.Hashtag
import co.uglytruth.hashtag.twitter.twitter_interface.TwitterAccessTokenInterface
import co.uglytruth.hashtag.twitter.type.TwitterAuthTypeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by tjw127 on 1/24/18.
 */
class TwitterRetrofitRequest{

    fun callTwitterAccessToken( context: Context){


        val webservice:Webservice = Webservice()

        webservice.getResponse(CredentialsEnum.TwitterBaseURL.value,context,"POST", parameter = "")
//        val gson:Gson = GsonBuilder()
//                .setLenient()
//                .create()
//        val retrofit: Retrofit = Retrofit.Builder()
//                .baseUrl(CredentialsEnum.TwitterBaseURL.value)
//
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build()
//
//        val tw: TwitterAccessTokenInterface = retrofit.create(TwitterAccessTokenInterface::class.java)
//
//        val twitterBearerToken:TwitterBearerToken = TwitterBearerToken()
//        val bearerTokenString:String = twitterBearerToken.getBearerToken()
//        val hashtag: Call<TwitterAccessToken> = tw.getTwitterApplicationOnlyAccessToken(authorization = "Basic $bearerTokenString", grant_type = "client_credentials")


//        Log.v("TwitterCallback", "Call")
//        hashtag.enqueue(object : Callback<TwitterAccessToken> {
//
//            override fun onResponse(call: Call<TwitterAccessToken>?, response: Response<TwitterAccessToken>?) {
//
//                if (response?.isSuccessful!!)
//                {
//                    Log.v("TwitterM", response.message())
//                    val hashtagResponse: TwitterAccessToken? = response.body()
//
//                    val result:ArrayList<TwitterAccessToken>? = ArrayList<TwitterAccessToken>()
//                    result?.add(hashtagResponse!!)
//
////                    var adapter: HashtagAdapter = HashtagAdapter(result, context)
////
////                    recyclerView.adapter = adapter
//
//
//
//
//
//                    Log.v("TwitterResponse", result?.toString())
//
//
//                }else{
//
//                    Log.v("TwitterCallback", response.raw().toString())
//
//                }
//
//
//            }
//            override fun onFailure(call: Call<TwitterAccessToken>?, t: Throwable?) {
//
//                Log.v("TwitterFailure", t?.localizedMessage)
//
//            }
//        })



    }
}