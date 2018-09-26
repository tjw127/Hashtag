package co.uglytruth.hashtag.twitter

import android.util.Base64
import co.uglytruth.hashtag.enum.CredentialsEnum
import java.nio.charset.Charset


/**
 * Created by tjw127 on 1/24/18.
 */
class TwitterBearerToken{



    fun getBearerToken():String{

        val consumerKeyByte:ByteArray = CredentialsEnum.TwitterConsumerKey.value.toByteArray(Charsets.UTF_8)
        val consumerSecretKeyByteArray:ByteArray = CredentialsEnum.TwitterConsumerSecret.value.toByteArray(Charsets.UTF_8)

        val consumerKeyBase64String:String = Base64.encodeToString(consumerKeyByte, Base64.NO_WRAP)
        val consumerSecretKeyBase64String:String = Base64.encodeToString(consumerSecretKeyByteArray, Base64.NO_WRAP)

        val bearerTokenCredentials:String = CredentialsEnum.TwitterConsumerKey.value + ":" + CredentialsEnum.TwitterConsumerSecret.value

        val bearerTokenCredentialsByteArray:ByteArray = bearerTokenCredentials.toByteArray(Charsets.UTF_8)
        val bearerTokenCredentialsEncodedBase64:String = Base64.encodeToString(bearerTokenCredentialsByteArray, Base64.NO_WRAP)

        return bearerTokenCredentialsEncodedBase64
    }
}