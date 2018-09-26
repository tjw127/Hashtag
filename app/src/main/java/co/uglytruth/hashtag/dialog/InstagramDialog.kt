package co.uglytruth.hashtag.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout



/**
 * Created by tjw127 on 1/22/18.
 */
class InstagramDialog(context: Context?) : Dialog(context) {

    val DIMENSIONS_LANDSCAPE = floatArrayOf(460f, 260f)
    val DIMENSIONS_PORTRAIT = floatArrayOf(280f, 420f)

    val MARGIN = 4
    val PADDING = 2

    val mUrl:String = ""

    val webView:WebView? = null






    val FILL = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}