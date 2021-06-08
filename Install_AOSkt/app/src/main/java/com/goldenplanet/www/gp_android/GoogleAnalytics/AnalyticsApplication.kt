package com.goldenplanet.Install_AOSkt

import android.app.Activity
import android.app.Application
import android.os.AsyncTask
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.firebase.analytics.FirebaseAnalytics
import java.io.IOException

class AnalyticsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        sAnalytics = GoogleAnalytics.getInstance(this)
        sAnalytics!!.setLocalDispatchPeriod(1)
        val task: GAID = GAID()
        task.execute()
        activity = Activity()
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    internal inner class GAID : AsyncTask<Void?, Void?, String>() {
        protected override fun doInBackground(vararg params: Void?): String? {
            var advertisingId = ""
            try {
                val info = AdvertisingIdClient.getAdvertisingIdInfo(this@AnalyticsApplication)
                advertisingId = info.id
            } catch (e: GooglePlayServicesNotAvailableException) {
                e.printStackTrace()
            } catch (e: GooglePlayServicesRepairableException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return advertisingId
        }

        override fun onPostExecute(id: String) {
            super.onPostExecute(id)
            GAID = id
        }
    }

    //            sTracker.enableAutoActivityTracking(true);
    @get:Synchronized
    val defaultTracker: Tracker?
        get() {
            if (sTracker == null) {
                sTracker = sAnalytics!!.newTracker(R.xml.global_tracker)
                sTracker!!.enableAdvertisingIdCollection(true)
                sTracker!!.enableExceptionReporting(true)
                sTracker!!.setAnonymizeIp(true)
                //            sTracker.enableAutoActivityTracking(true);
            }
            return sTracker
        }

    companion object {
        private var sAnalytics: GoogleAnalytics? = null
        var sTracker: Tracker? = null
        var GAID = ""
        var APPCheck = "Dev"
        var mFirebaseAnalytics: FirebaseAnalytics? = null
        var activity: Activity? = null
    }
}