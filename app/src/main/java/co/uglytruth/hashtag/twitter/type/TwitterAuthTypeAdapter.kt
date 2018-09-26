package co.uglytruth.hashtag.twitter.type
import android.util.Log
import co.uglytruth.hashtag.twitter.TwitterAccessToken
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

/**
 * Created by tjw127 on 1/25/18.
 */
class TwitterAuthTypeAdapter : TypeAdapter<TwitterAccessToken>() {
    override fun read(`in`: JsonReader?): TwitterAccessToken {
        Log.v("TwitterAuthTypeAdapter", `in`?.path)

        val twitterAccessToken:TwitterAccessToken = TwitterAccessToken(access_token = "", token_type = "")
        return twitterAccessToken
    }

    override fun write(out: JsonWriter?, value: TwitterAccessToken?) {

    }


}