package com.goldenplanet.Install_AOSkt

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class PackageReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val packageName = intent.data!!.schemeSpecificPart
        val action = intent.action
        if (action == Intent.ACTION_PACKAGE_ADDED) {
            Log.d("GADATA", "앱설치")
        } else if (action == Intent.ACTION_PACKAGE_REMOVED) {
            Log.d("GADATA", "앱삭제")
            val prefs = context.getSharedPreferences("isFirst", Context.MODE_PRIVATE)
            val isFirst = prefs.getBoolean("isFirst", false)
            if (isFirst) {
                prefs.edit().remove("isFirst").commit()
            }
        }
    }

    companion object {
        private const val TAG = "PackageReceiver"
    }
}