package co.uglytruth.hashtag.hashtag

import android.support.v7.widget.RecyclerView
import android.util.Log
import co.uglytruth.hashtag.adapter.HashtagAdapter
import co.uglytruth.hashtag.endpoint.Endpoint
import co.uglytruth.hashtag.enum.CredentialsEnum
import co.uglytruth.hashtag.hashtag.retrofit_interfaces.HashtagInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

/**
 * Created by tjw127 on 1/14/18.
 */

class HashtagRetrofitRequest{

    fun callHastagData(recyclerView:RecyclerView){


        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(CredentialsEnum.GoogleComputeEngineURL.value)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val hashtagInterface:HashtagInterface = retrofit.create(HashtagInterface::class.java)

        val hashtag:Call<List<Hashtag>> = hashtagInterface.getHashtagData(key = CredentialsEnum.GoogleComputeEngineKey.value)


        hashtag.enqueue(object : Callback<List<Hashtag>>{

            override fun onResponse(call: Call<List<Hashtag>>?, response: Response<List<Hashtag>>?) {

                if (response?.isSuccessful!!)
                {
                    val hashtagResponse: List<Hashtag>? = response.body()

                    val result:ArrayList<Hashtag>? = ArrayList<Hashtag>()
                    result?.addAll(hashtagResponse!!)

                    var adapter:HashtagAdapter = HashtagAdapter(result)

                    recyclerView.adapter = adapter





                    Log.v("HashtagResponse", result?.toString())


                }else{

                    Log.v("HashtagCallback", "Response is null")

                }


            }
            override fun onFailure(call: Call<List<Hashtag>>?, t: Throwable?) {

                Log.v("Failure", t.toString())

            }
        })



    }
}