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
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageInfo
import android.preference.PreferenceManager
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.util.Base64
import com.google.android.gms.analytics.GoogleAnalytics
import co.uglytruth.hashtag.analytics.KAnalyticsApplication
import co.uglytruth.hashtag.enum.CredentialsEnum
import co.uglytruth.hashtag.twitter.TwitterRequest
import co.uglytruth.hashtag.twitter.TwitterRetrofitRequest
import co.uglytruth.hashtag.twitter.TwitterStatuses
import co.uglytruth.hashtag.twitter.rest.TWTweets
import co.uglytruth.hashtag.twitter.rest.gson.TWTweetsGson
import co.uglytruth.hashtag.yahoo.Yahoo
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.Tracker
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), MoPubView.BannerAdListener, MoPubInterstitial.InterstitialAdListener  {



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
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /*
        try {

            PackageInfo info =
getPackageManager().getPackageInfo("your package name",
PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("MY KEY HASH:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
         */

        try{
            val info:PackageInfo = packageManager.getPackageInfo("co.uglytruth.hashtag", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures){
                val md:MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.v("My Key Hash: ", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }

        }catch (e: Exception){

           Log.v("My Key Hash", e.localizedMessage)
        }



//        val tw = TwitterRetrofitRequest()
//        tw.callTwitterAccessToken(this)



//        val tw = TwitterRequest()
//        tw.callAccessToken(context = this)

//        val yahoo = Yahoo()
//        yahoo.getLocation("Atlanta GA", this)

//        val sharedPreferences:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
//
//        val shared:String = sharedPreferences.getString("TwitterJSON", "No Data")
//
//
//        val gsonTw:TWTweetsGson = TWTweetsGson()
//        val statuses:TwitterStatuses = gsonTw.parseData(shared)
//
//        var s:Int = 0
//        for (status in statuses.statuses)
//        {
//            Log.v("TwitterJSON", "" + statuses.statuses.get(s).entities.hashtags.count())
//            s += 1
//        }

//        val editor:SharedPreferences.Editor = sharedPreferences.edit()


        loadHastag()


//        val twtweets:TWTweets = TWTweets()

//        val q = hashMapOf<String, String>("q" to "tech")
//        twtweets.search(q = q, context = this, locale = null, geocode = null, lang = null, result_type = null, count = null, since_id = null, max_id = null, include_entities = null, until = null)
//


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


        mopubView = findViewById(R.id.adview1)
        mopubView?.adUnitId = CredentialsEnum.MoPubViewAdUnit.value
        mopubView?.bannerAdListener = this
        mopubView?.loadAd()

//       interstitial = MoPubInterstitial(this, CredentialsEnum.MoPubInterstitial.value)
//       interstitial?.interstitialAdListener = this
//       interstitial?.load()
    }




    override fun onStart() {
        super.onStart()

//        try {
//
//            loadHastag()
//
//        }catch(e: Exception){
//
//            e.printStackTrace()
//        }

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
//        val request:HashtagRetrofitRequest = HashtagRetrofitRequest()





        val recyclerView:RecyclerView = findViewById(R.id.hashtagRecyclerView)
//
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)


        val hastag:String = CredentialsEnum.Hashtag.value

        val gson: Gson = GsonBuilder().create()

        Log.v("HashtagList12", hastag)

        val type = object: TypeToken<ArrayList<Hashtag>>(){}.type

        val hastagList  = gson.fromJson<ArrayList<Hashtag>>(hastag, type)

        Log.v("HashtagList1", " " + hastagList[0])
//
        var adapter:HashtagAdapter = HashtagAdapter(hastagList, this)
//
        recyclerView.adapter = adapter


//        request.callHastagData(recyclerView = recyclerView, context = this)
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
//                loadHastag()
                timer()
                }

            else -> super.onOptionsItemSelected(item)
        }
    }

    inline fun consume (f: () -> Unit): Boolean {

        f()
        return true

    }

    override fun onBackPressed() {

            super.onBackPressed()


    }


}
