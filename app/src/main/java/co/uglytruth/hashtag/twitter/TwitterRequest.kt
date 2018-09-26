package co.uglytruth.hashtag.twitter

import android.content.Context
import co.uglytruth.hashtag.enum.CredentialsEnum
import co.uglytruth.hashtag.twitter.client.TwitterClient

/**
 * Created by tjw127 on 1/29/18.
 */

class TwitterRequest{

    fun callAccessToken(context: Context)
    {
        val url = CredentialsEnum.TwitterBaseURL.value + "oauth2/token"

        val twitterClient:TwitterClient = TwitterClient()
        twitterClient.getAccessToken(url = url, context = context, parameters = "grant_type=client_credentials");
    }
}