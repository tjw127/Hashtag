package co.uglytruth.hashtag.hashtag.retrofit_interfaces

import co.uglytruth.hashtag.enum.CredentialsEnum
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import co.uglytruth.hashtag.hashtag.Hashtag
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by tjw127 on 1/14/18.
 */

 interface HashtagInterface{



    @GET("hashtag.php")
    fun getHashtagData(@Query("key") key: String) :Call<List<Hashtag>>

}
