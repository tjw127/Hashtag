package co.uglytruth.hashtag.analytics

import android.app.Application
import co.uglytruth.hashtag.R
import co.uglytruth.hashtag.enum.CredentialsEnum

import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker
/**
 * Created by tjw127 on 1/22/18.
 */

class KAnalyticsApplication : Application() {

    private var mTracker: Tracker? = null
    private var analytics: GoogleAnalytics? = null

    val defaultTracker: Tracker?
        @Synchronized get() {


                mTracker = analytics!!.newTracker(CredentialsEnum.GoogleAnalytics.value)

                mTracker?.enableAutoActivityTracking(true)
                mTracker?.enableExceptionReporting(false)


            return mTracker
        }

    override fun onCreate() {
        super.onCreate()

        analytics = GoogleAnalytics.getInstance(this)


    }
}