package co.uglytruth.hashtag

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import co.uglytruth.hashtag.analytics.KAnalyticsApplication
import co.uglytruth.hashtag.enum.CredentialsEnum
import co.uglytruth.hashtag.hashtag.Hashtag
import co.uglytruth.hashtag.hashtag.HashtagRetrofitRequest
import com.crashlytics.android.Crashlytics
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.Tracker
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.TypeAdapterFactory
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial
import com.mopub.mobileads.MoPubView
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.app_bar_main3.*
import java.util.ArrayList

class Main3Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MoPubView.BannerAdListener, MoPubInterstitial.InterstitialAdListener {
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

    var hashtagList: ArrayList<Hashtag>? = ArrayList<Hashtag>()
    var mopubView: MoPubView? = null
    var interstitial:MoPubInterstitial? = null
    var firebaseAnalytics: FirebaseAnalytics? = null
    var progressBar: ProgressBar? = null
    private var sAnalytics: GoogleAnalytics? = null
    private var sTracker: Tracker? = null
    var analyticsApplication: KAnalyticsApplication? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        setContentView(R.layout.activity_main3)
        setSupportActionBar(toolbar)



        analyticsApplication = application as KAnalyticsApplication?
        sTracker = GoogleAnalytics.getInstance(this).newTracker(CredentialsEnum.GoogleAnalytics.value)
        sTracker?.enableAutoActivityTracking(true)
        sendActivityAnalytics()



        createDialog()
//        firebase()
        turnOnLocation()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
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


//        mopubView = findViewById(R.id.adview)
//        mopubView?.adUnitId = CredentialsEnum.MoPubViewAdUnit.value
//        mopubView?.bannerAdListener = this
//        mopubView?.loadAd()

//       interstitial = MoPubInterstitial(this, CredentialsEnum.MoPubInterstitial.value)
//       interstitial?.interstitialAdListener = this
//       interstitial?.load()
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

        try {

            getMoPub()

        }catch (e:Exception)
        {
            e.printStackTrace()
        }

        timer()

    }




    override fun onResume() {
        super.onResume()
        sendActivityAnalytics()
//        getMoPub()

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
        val request: HashtagRetrofitRequest = HashtagRetrofitRequest()


        Log.v("HashtagList1", hashtagList.toString())


        val recyclerView: RecyclerView = findViewById(R.id.hashtagRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)

        request.callHastagData(recyclerView = recyclerView, context = this)
    }





    inline fun consume (f: () -> Unit): Boolean {

        f()
        return true

    }





    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main3, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
