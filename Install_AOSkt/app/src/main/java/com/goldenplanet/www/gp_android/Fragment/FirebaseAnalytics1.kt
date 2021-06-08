package com.goldenplanet.www.gp_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.goldenplanet.Install_AOSkt.R
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalytics : Fragment() {
    var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_firebase_analytics, container, false)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context!!)
        val params = Bundle()
        params.putString("ScreenDepth1", "screen_depth1_" + TAG)
        params.putString("ScreenDepth2", "screen_depth2_" + TAG)
        params.putString("ScreenDepth3", "screen_depth3_" + TAG)
        params.putString("ScreenDepth4", "screen_depth4_" + TAG)
        params.putString("ScreenDepth5", "screen_depth5_" + TAG)
        params.putFloat("FloatData_" + TAG, 1f)
        params.putDouble("DoubleData_" + TAG, 1.0)
        params.putInt("IntData_" + TAG, 1)
        mFirebaseAnalytics!!.logEvent("screenview", params)
        val onClickListener: btnSetOnClickListener = btnSetOnClickListener()
        TestA = v.findViewById(R.id.btn_firebase_analytics_a)
        TestA!!.setOnClickListener(onClickListener)
        TestB = v.findViewById(R.id.btn_firebase_analytics_b)
        TestB!!.setOnClickListener(onClickListener)
        APPCALL = v.findViewById(R.id.btn_call_bapp)
        APPCALL!!.setOnClickListener(onClickListener)
        return v
    }

    inner class btnSetOnClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            when (v.id) {
                R.id.btn_firebase_analytics_a -> mFirebaseAnalytics!!.setUserProperty("PRO1", "TestA")
                R.id.btn_firebase_analytics_b -> mFirebaseAnalytics!!.setUserProperty("PRO1", "TestB")
            }
        }
    }

    companion object {
        private var TestA: Button? = null
        private var TestB: Button? = null
        private var APPCALL: Button? = null
        private const val TAG = "FirebaseAnalytics"
    }
}