package co.uglytruth.hashtag.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import co.uglytruth.hashtag.R
import co.uglytruth.hashtag.const_variable.Cred_Consts
import co.uglytruth.hashtag.listener.AuthenticationListener

import android.webkit.WebViewClient
import android.widget.Toast

/**
 * Created by tjw127 on 1/23/18.
 */

class JavaInstagramDialog : Dialog {

    private var listener: AuthenticationListener? = null
    var context_parent: Context? = null

    private var web_view: WebView? = null

    private val url = (Cred_Consts.Base_URL
            + "oauth/authorize/?client_id="
            + Cred_Consts.Client_ID
            + "&redirect_uri="
            + Cred_Consts.Redirect_URL
            + "&response_type=token"
            + "&display=touch&scope=public_content")


    constructor(context: Context, listener: AuthenticationListener) : super(context) {
        this.context_parent = context
        this.listener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.instagram_dialog)
        Log.v("JavaInsta", "Start")
        initializeWebView()
    }

    constructor(context: Context) : super(context) {
        this.context_parent = context
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {}

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {}

    private fun initializeWebView() {
        web_view = findViewById(R.id.web_view)
        web_view!!.loadUrl(url)
        web_view!!.webViewClient = object : WebViewClient() {

            internal var authComplete = false

            internal lateinit var access_token: String
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                if (url!!.contains("#access_token=") && !authComplete) {
                    val uri = Uri.parse(url)
                    access_token = uri.encodedFragment
                    // get the whole token after the '=' sign
                    access_token = access_token.substring(access_token.lastIndexOf("=") + 1)
                    Log.i("", "CODE : " + access_token)
                    authComplete = true
                    listener!!.onCodeReceived(access_token)
                    dismiss()

                } else if (url!!.contains("?error")) {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
            }
        }
    }
}
