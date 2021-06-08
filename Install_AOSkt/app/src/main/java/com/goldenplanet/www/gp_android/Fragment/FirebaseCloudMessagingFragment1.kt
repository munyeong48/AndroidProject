package com.goldenplanet.Install_AOSkt

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging

class FirebaseCloudMessagingFragment : Fragment() {
    var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
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
        val v = inflater.inflate(R.layout.fragment_firebase_cloud_messaging, container, false)
        val mFirebaseAnalytics: FirebaseAnalytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context!!)
        mFirebaseAnalytics.setUserProperty("PRO1", "FCM")
        val onClickListener: btnSetOnClickListener = btnSetOnClickListener()
        subscribeButton = v.findViewById(R.id.subscribeButton)
        subscribeButton!!.setOnClickListener(onClickListener)
        subscribeButton_GPTEST = v.findViewById(R.id.subscribeButton_GPTEST)
        subscribeButton_GPTEST!!.setOnClickListener(onClickListener)
        logTokenButton = v.findViewById(R.id.logTokenButton)
        logTokenButton!!.setOnClickListener(onClickListener)
        return v
    }

    inner class btnSetOnClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            when (v.id) {
                R.id.logTokenButton -> {
                    FirebaseInstanceId.getInstance().instanceId
                            .addOnCompleteListener { task -> Log.e(TAG, task.result!!.token) }
                    FirebaseInstanceId.getInstance().instanceId
                            .addOnCompleteListener(OnCompleteListener { task ->
                                if (!task.isSuccessful) {
                                    return@OnCompleteListener
                                }
                                val token = task.result!!.token
                                val msg = getString(R.string.msg_token_fmt, token)
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                //Log.e(TAG + "토큰 가짜", token)
                            })
                }
                R.id.subscribeButton_GPTEST -> FirebaseMessaging.getInstance().subscribeToTopic("GPTEST")
                        .addOnCompleteListener { task ->
                            var msg = "GPTEST 토픽 구독 좋아요!"
                            if (!task.isSuccessful) {
                                msg = getString(R.string.msg_subscribe_failed)
                            }
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                R.id.subscribeButton -> FirebaseMessaging.getInstance().subscribeToTopic("Cosmetic")
                        .addOnCompleteListener { task ->
                            var msg = "화장품 토픽 구독 좋아요!"
                            if (!task.isSuccessful) {
                                msg = getString(R.string.msg_subscribe_failed)
                            }
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
            }
        }
    }

    companion object {
        private var logTokenButton: Button? = null
        private var subscribeButton: Button? = null
        private var subscribeButton_GPTEST: Button? = null
        private const val TAG = "CloudMessagingFragment"
    }
}