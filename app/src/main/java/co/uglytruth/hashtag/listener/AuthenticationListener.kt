package co.uglytruth.hashtag.listener

/**
 * Created by tjw127 on 1/23/18.
 */

interface AuthenticationListener {

    fun onCodeReceived(auth_token: String)
}
