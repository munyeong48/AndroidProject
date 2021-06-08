package app.suro.myapplication

import android.app.Application
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker

class AnalyticsApplication : Application() {
    private var sAnalytics: GoogleAnalytics? = null
    private var sTracker : Tracker? = null

    override fun onCreate() {
        super.onCreate()
        sAnalytics = GoogleAnalytics.getInstance(this)
    }

    @Synchronized
    fun getDefaultTracker(): Tracker? {
        if(sTracker == null) {
            sAnalytics?.let {
                sTracker = it.newTracker(R.xml.global_tracker)
                sTracker?.enableAdvertisingIdCollection(true)
                sTracker?.enableExceptionReporting(true)
                sTracker?.setAnonymizeIp(true)
            }
        }
        return sTracker
    }
}