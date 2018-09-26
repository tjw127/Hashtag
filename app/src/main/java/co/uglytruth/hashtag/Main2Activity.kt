package co.uglytruth.hashtag

import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import co.uglytruth.hashtag.const_variable.Cred_Consts
import co.uglytruth.hashtag.dialog.InstagramDialog
import co.uglytruth.hashtag.dialog.JavaInstagramDialog
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
         val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

//        val webView:WebView = findViewById(R.id.web_view)
//        webView.settings.javaScriptEnabled = true
//        webView.loadUrl(Cred_Consts.Redirect_URL)

        Log.v("JAVA", "Start")


//        initializeWebView()

//        val instagramDialog:JavaInstagramDialog = JavaInstagramDialog(this)
//        instagramDialog.setContentView(R.layout.instagram_dialog)
//        instagramDialog.setTitle("Instagram")
//        instagramDialog.show()
    }


    private val url = (Cred_Consts.Base_URL
            + "oauth/authorize/?client_id="
            + Cred_Consts.Client_ID
            + "&redirect_uri="
            + Cred_Consts.Redirect_URL
            + "&response_type=token"
            + "&display=touch&scope=public_content")
//    private fun initializeWebView() {
//
//        val web_view:WebView = findViewById(R.id.web_view)
//        web_view.loadUrl(url)
//        web_view.setWebViewClient(object : WebViewClient() {
//
//            internal var authComplete = false
//
//            internal lateinit var access_token: String
//            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
//                super.onPageStarted(view, url, favicon)
//            }
//
//            override fun onPageFinished(view: WebView, url: String) {
//                super.onPageFinished(view, url)
//
//                if (url.contains("#access_token=") && !authComplete) {
//                    val uri = Uri.parse(url)
//                    access_token = uri.encodedFragment
//                    // get the whole token after the '=' sign
//                    access_token = access_token.substring(access_token.lastIndexOf("=") + 1)
//                    Log.i("", "CODE : " + access_token)
//                    authComplete = true
////                    listener.onCodeReceived(access_token)
////                    dismiss()
//
//                } else if (url.contains("?error")) {
////                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
////                    dismiss()
//                }
//            }
//        })
//    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
