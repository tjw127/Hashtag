package co.uglytruth.hashtag.instagram

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.webkit.WebView
import co.uglytruth.hashtag.MainActivity
import co.uglytruth.hashtag.R
import co.uglytruth.hashtag.enum.CredentialsEnum
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import io.fabric.sdk.android.Fabric


import kotlinx.android.synthetic.main.activity_instagram.*
import java.math.BigDecimal
import java.util.*

class InstagramActivity : AppCompatActivity() {
//    private var webView: WebView? = null
    private var sp: SharedPreferences? = null
    private var callbackManager: CallbackManager? = null
    private var context: Context? = null
    private var twitter_login: TwitterLoginButton?  = null
    private var TwitterKey = "mI4Rrxkh5qRtz68CDnIuIWQ3V";
    private var TwitterSecret = "bN2aErm8wq4IlQb2t044ODpAS7W7fcdYdCWql3otN7EQbdmrid";
    private var authConfig: TwitterAuthConfig? = null
    private var config: TwitterConfig? = null
    private var login_switch = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(this)
        authConfig = TwitterAuthConfig(TwitterKey, TwitterSecret)
        config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(authConfig)
                .debug(true)
                .build()

        Twitter.initialize(config)





        setContentView(R.layout.activity_instagram)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)



        context = this
        sp = getPreferences(Context.MODE_PRIVATE)

        callbackManager = CallbackManager.Factory.create()

        val loginButton = findViewById<LoginButton>(R.id.login_button)

        twitter_login = findViewById<TwitterLoginButton>(R.id.twitter_login_button)


        val callback: Callback<TwitterSession> = (object  : Callback<TwitterSession>(){

            override fun success(result: Result<TwitterSession>?) {

                Log.v("Twitter12", " " + result.toString())
                login_switch = 1
//                val session = TwitterCore.getInstance().sessionManager.activeSession
                val twitterSession: TwitterSession = result?.data!!

                val intent: Intent = Intent(context, MainActivity::class.java)
                val editor = sp?.edit()
                editor?.putString("tw_secret", result.data.authToken.secret.toString())
                editor?.putString("tw_token", result.data.authToken.token.toString())
                editor?.putString("tw_user_id", twitterSession.userId.toString())
                editor?.putString("tw_user_name", twitterSession.userName.toString())
                editor?.apply()

                Log.v("Twitter", " " + twitterSession.userId.toString() + " " + twitterSession.userName.toString())

                startActivity(intent)

            }
            override fun failure(exception: TwitterException?) {
                Log.v("TwitterException", " " + exception?.localizedMessage)

            }
        })

        twitter_login?.callback = callback

        /*

AccessToken accessToken = AccessToken.getCurrentAccessToken();
boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

         */

        /*
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
         */

        loginButton.setReadPermissions(arrayListOf("public_profile"))

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

             override fun onSuccess(result: LoginResult) {
                // App code

                 login_switch = 2
                 Log.v("FBLoginTy", " " + result.accessToken.token)
                 val intent: Intent = Intent(context, MainActivity::class.java)
                 val editor = sp?.edit()

                 editor?.putString("fb_token", result.accessToken.token)
                 editor?.apply()
                 startActivity(intent)
            }

            override fun onCancel() {
                // App code
            }

            override fun onError( exception: FacebookException) {
                // App code
            }
        })

        val logger = AppEventsLogger.newLogger(this)
        logger.logPurchase(BigDecimal.valueOf(4.32), Currency.getInstance("USD"))

//        webView = findViewById(R.id.insta_webview)
//        webView?.getSettings()!!.setAppCacheEnabled(true)
//        webView?.getSettings()!!.javaScriptEnabled = true
//        webView?.isHorizontalScrollBarEnabled = false
//        webView?.isVerticalScrollBarEnabled = false
//        webView?.setWebViewClient(InstagramWebClient())
//
//        val url:String = CredentialsEnum.Instagram_BASE_URL.value + CredentialsEnum.Instagram_AUTHORIZE_ENDPOINT.value + "/?client_id=" + CredentialsEnum.Instagram_CLIENT_ID + "&redirect_uri=" + CredentialsEnum.Instagram_AUTHORIZATION_REDIRECT_URI + "&response_type=" + CredentialsEnum.Instagram_RESPONSE_TYPE
//
//        webView?.loadUrl(url)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        twitter_login?.onActivityResult(requestCode, resultCode, data)

        /*

         when (login_switch){
             1 ->
             2 -> callbackManager!!.onActivityResult(requestCode, resultCode, data);
        }

         */



    }

}
