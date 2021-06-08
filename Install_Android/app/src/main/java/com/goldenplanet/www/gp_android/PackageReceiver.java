package com.goldenplanet.www.gp_android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class PackageReceiver extends BroadcastReceiver {

    private static String TAG = "PackageReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String packageName = intent.getData().getSchemeSpecificPart();
        String action = intent.getAction();
        if(action.equals(Intent.ACTION_PACKAGE_ADDED)) {
            Log.d("GADATA", "앱설치");
        }
        else if(action.equals(Intent.ACTION_PACKAGE_REMOVED)) {
            Log.d("GADATA", "앱삭제");

            SharedPreferences prefs = context.getSharedPreferences("isFirst", Context.MODE_PRIVATE);
            boolean isFirst = prefs.getBoolean("isFirst", false);
            if(isFirst){
                prefs.edit().remove("isFirst").commit();
            }
        }

    }
}
