package co.uglytruth.hashtag

import android.content.DialogInterface
import android.widget.Button
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import co.uglytruth.hashtag.adapter.HashtagAdapter
import co.uglytruth.hashtag.endpoint.Endpoint
import co.uglytruth.hashtag.hashtag.Hashtag
import co.uglytruth.hashtag.hashtag.HashtagRetrofitRequest
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.mopub.common.MoPub
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial
import com.mopub.mobileads.MoPubView
import io.fabric.sdk.android.Fabric
import android.app.Application
import com.google.android.gms.analytics.GoogleAnalytics
import co.uglytruth.hashtag.analytics.KAnalyticsApplication
import co.uglytruth.hashtag.enum.CredentialsEnum
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.Tracker

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), MoPubView.BannerAdListener, MoPubInterstitial.InterstitialAdListener, NavigationView.OnNavigationItemSelectedListener  {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       return true
    }

    override fun onInterstitialLoaded(interstitial: MoPubInterstitial?) {
    }

    override fun onInterstitialShown(interstitial: MoPubInterstitial?) {
    }

    override fun onInterstitialFailed(interstitial: MoPubInterstitial?, errorCode: MoPubErrorCode?) {
    }

    override fun onInterstitialDismissed(interstitial: MoPubInterstitial?) {
    }

    override fun onInterstitialClicked(interstitial: MoPubInterstitial?) {
    }

    override fun onBannerExpanded(banner: MoPubView?) {
    }

    override fun onBannerLoaded(banner: MoPubView?) {
    }

    override fun onBannerCollapsed(banner: MoPubView?) {
    }

    override fun onBannerFailed(banner: MoPubView?, errorCode: MoPubErrorCode?) {
    }

    override fun onBannerClicked(banner: MoPubView?) {
    }

    var hashtagList:ArrayList<Hashtag>? = ArrayList<Hashtag>()
    var mopubView: MoPubView? = null
    var interstitial:MoPubInterstitial? = null
    var firebaseAnalytics:FirebaseAnalytics? = null
    var progressBar:ProgressBar? = null
    private var sAnalytics: GoogleAnalytics? = null
    private var sTracker: Tracker? = null
    var analyticsApplication: KAnalyticsApplication? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //var url = Endpoint.Builder().baseUrl().hashtag().key().build()



        analyticsApplication = application as KAnalyticsApplication?
        sTracker = GoogleAnalytics.getInstance(this).newTracker(CredentialsEnum.GoogleAnalytics.value)
        sTracker?.enableAutoActivityTracking(true)
        sendActivityAnalytics()


        createDialog()
//        firebase()
        turnOnLocation()




//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
    }

    fun firebase(){

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        firebaseAnalytics?.setCurrentScreen(this, "MainActivity", null)

        val bundle: Bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "12345")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "MainActivity")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "hashtag")
        firebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    fun createDialog()
    {
        progressBar = this.findViewById(R.id.progressBar2)
        progressBar?.visibility = View.VISIBLE


    }

    fun getMoPub(){

        mopubView = findViewById(R.id.adview)
        mopubView?.adUnitId = CredentialsEnum.MoPubViewAdUnit.value
        mopubView?.bannerAdListener = this
        mopubView?.loadAd()

       interstitial = MoPubInterstitial(this, CredentialsEnum.MoPubInterstitial.value)
       interstitial?.interstitialAdListener = this
       interstitial?.load()
    }


    fun turnOnLocation(){

        if (((ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)) != PackageManager.PERMISSION_GRANTED) && ((ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED)){

            val list = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

            ActivityCompat.requestPermissions(this, list, 1)



        }
    }

    override fun onStart() {
        super.onStart()

        try {

            loadHastag()

        }catch(e: Exception){

            e.printStackTrace()
        }

        timer()

    }




    override fun onResume() {
        super.onResume()
        sendActivityAnalytics()
        getMoPub()

    }

    fun sendActivityAnalytics()
    {
        try {
            sTracker?.setScreenName("MainActivity")
            sTracker?.send(HitBuilders.ScreenViewBuilder().build())
            Log.v("GoogleAnaly", "Start")

        }catch (e: Exception){

            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
    }

    fun timer()
    {

      Handler().postDelayed({
          progressBar?.visibility = View.INVISIBLE
      }, 5000)

//      Handler().postDelayed({
//
//          val alertDialog = AlertDialog.Builder(this).create()
//          alertDialog.setTitle("Reload")
//          alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
//              dialogInterface, i -> dialogInterface.dismiss()
//          })
//          val s = "You have to Reload the page."
//
//
//      }, 7000)
    }



    override fun onDestroy() {
        super.onDestroy()
        mopubView?.destroy()
//        interstitial?.destroy()
    }

    private fun loadHastag()
    {
        val request:HashtagRetrofitRequest = HashtagRetrofitRequest()


        Log.v("HashtagList1", hashtagList.toString())


        val recyclerView:RecyclerView = findViewById(R.id.hashtagRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)

        request.callHastagData(recyclerView = recyclerView, context = this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle click s on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_reload -> consume {

                progressBar?.visibility = View.VISIBLE
                loadHastag()
                timer()
                }

            else -> super.onOptionsItemSelected(item)
        }
    }

    inline fun consume (f: () -> Unit): Boolean {

        f()
        return true

    }


}
