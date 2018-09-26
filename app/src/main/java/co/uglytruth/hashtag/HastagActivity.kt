package co.uglytruth.hashtag

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Toast
import co.uglytruth.hashtag.const_variable.Cred_Consts
import co.uglytruth.hashtag.dialog.JavaInstagramDialog
import co.uglytruth.hashtag.enum.CredentialsEnum
import co.uglytruth.hashtag.listener.AuthenticationListener
import com.crashlytics.android.Crashlytics

import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import io.fabric.sdk.android.Fabric
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig



class HastagActivity : AppCompatActivity(), AuthenticationListener {
    override fun onCodeReceived(auth_token: String) {
        Log.v("Java_Token", auth_token)
    }

    var twitterLoginButton:TwitterLoginButton? = null

    private val url = (Cred_Consts.Base_URL
            + "oauth/authorize/?client_id="
            + Cred_Consts.Client_ID
            + "&redirect_uri="
            + Cred_Consts.Redirect_URL
            + "&response_type=token"
            + "&display=touch&scope=public_content")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(TwitterAuthConfig(CredentialsEnum.TwitterConsumerKey.value, CredentialsEnum.TwitterConsumerSecret.value))
                .debug(true)
                .build()
        Twitter.initialize(config)
        Fabric.with(this, Crashlytics())
        setContentView(R.layout.activity_hastag)

        twitterLoginButton = findViewById(R.id.login_button)
        twitterLoginButton?.setCallback(object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                val session = result.data
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                val msg = "@" + session.userName + " logged in! (#" + session.userId + ")"


                Log.v("Twitter", " " + msg)

                Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
            }

            override fun failure(exception: TwitterException) {
                Log.d("TwitterKit", "Login with Twitter failure", exception)
            }
        })

//        val instagramDialog:JavaInstagramDialog = JavaInstagramDialog(this, this)
//
//        instagramDialog.setTitle("Instagram")
//        instagramDialog.show()

//        val webView:WebView = findViewById(R.id.web_view)
//        webView.settings.javaScriptEnabled = true
//        webView.loadUrl(url)


        Log.v("Ty_Java", "Hello World")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        twitterLoginButton?.onActivityResult(requestCode, resultCode, data)
    }
}
