package com.goldenplanet.Install_AOSkt

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class RemoteConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote_config)
        val editText2 = findViewById<EditText>(R.id.editText2)
        imageView_one_logo = findViewById(R.id.imageView_one_logo)
        imageView_two_logo = findViewById(R.id.imageView_two_logo)
        imageView_three_logo = findViewById(R.id.imageView_three_logo)
        imageView_four_logo = findViewById(R.id.imageView_four_logo)
        imageView_five_logo = findViewById(R.id.imageView_five_logo)
        imageView_six_logo = findViewById(R.id.imageView_six_logo)
        val onClickListener: btnSetOnClickListener = btnSetOnClickListener()
        imageView_one_logo!!.setOnClickListener(onClickListener)
        imageView_two_logo!!.setOnClickListener(onClickListener)
        imageView_three_logo!!.setOnClickListener(onClickListener)
        imageView_four_logo!!.setOnClickListener(onClickListener)
        imageView_five_logo!!.setOnClickListener(onClickListener)
        imageView_six_logo!!.setOnClickListener(onClickListener)
        sns_layout = findViewById(R.id.sns_layout)
        textView2 = findViewById(R.id.textView2)
        val value = getConfigValue("json_layout")
        val title = getConfigValue("TEST")
        try {
            val jsonArray = JSONArray(value)
            for (i in 0 until jsonArray.length()) {
                val explrObject = jsonArray.getJSONObject(i)
                if (explrObject.getString("id").contains("text")) {
                    if ("textView2" == explrObject.getString("id")) setTextView(textView2, explrObject)
                } else if (explrObject.getString("id").contains("layout")) {
                    if ("sns_layout" == explrObject.getString("id")) setLinearLayout(sns_layout, explrObject)
                } else if (explrObject.getString("id").contains("image")) {
                    if ("imageView_one_logo" == explrObject.getString("id")) setImageView(imageView_one_logo, explrObject)
                    if ("imageView_two_logo" == explrObject.getString("id")) setImageView(imageView_two_logo, explrObject)
                    if ("imageView_three_logo" == explrObject.getString("id")) setImageView(imageView_three_logo, explrObject)
                    if ("imageView_four_logo" == explrObject.getString("id")) setImageView(imageView_four_logo, explrObject)
                    if ("imageView_five_logo" == explrObject.getString("id")) setImageView(imageView_five_logo, explrObject)
                    if ("imageView_six_logo" == explrObject.getString("id")) setImageView(imageView_six_logo, explrObject)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message!!)
        }
    }

    inner class btnSetOnClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            when (v.id) {
                R.id.imageView_one_logo -> {
                }
                R.id.imageView_two_logo -> {
                }
                R.id.imageView_three_logo -> {
                }
                R.id.imageView_four_logo -> {
                }
                R.id.imageView_five_logo -> {
                }
                R.id.imageView_six_logo -> {
                    val params = Bundle()
                    val mFirebaseAnalytics = FirebaseAnalytics.getInstance(this@RemoteConfigActivity)
                    params.putString("여섯번째이벤트눌렀다", "아자" + TAG)
                    mFirebaseAnalytics.logEvent("custom_event", params)
                }
                R.id.subscribeButton -> {
                }
            }
        }
    }

    fun setTextView(textView: TextView?, jsonObject: JSONObject) {
        try {
            val lp = textView!!.layoutParams as RelativeLayout.LayoutParams
            var marginTop = 0
            var marginLeft = 0
            var marginBottom = 0
            var marginRight = 0
            if (JsonObjectFindKey(jsonObject, "marginTop") != null) marginTop = Integer.valueOf(JsonObjectFindKey(jsonObject, "marginTop")!!)
            if (JsonObjectFindKey(jsonObject, "marginLeft") != null) marginLeft = Integer.valueOf(JsonObjectFindKey(jsonObject, "marginLeft")!!)
            if (JsonObjectFindKey(jsonObject, "marginBottom") != null) marginBottom = Integer.valueOf(JsonObjectFindKey(jsonObject, "marginBottom")!!)
            if (JsonObjectFindKey(jsonObject, "marginRight") != null) marginRight = Integer.valueOf(JsonObjectFindKey(jsonObject, "marginRight")!!)
            lp.setMargins(marginLeft, marginTop, marginRight, marginBottom)
            textView.layoutParams = lp
        } catch (e: Exception) {
            Log.e(TAG, e.message!!)
        }
    }

    private fun setLinearLayout(linearLayout: LinearLayout?, jsonObject: JSONObject) {
        try {
            val params = linearLayout!!.layoutParams as RelativeLayout.LayoutParams
            if (JsonObjectFindKey(jsonObject, "alignParentBottom") != null && JsonObjectFindKey(jsonObject, "alignParentBottom") == "true") params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            if (JsonObjectFindKey(jsonObject, "alignParentBottom") != null && JsonObjectFindKey(jsonObject, "alignParentBottom") == "false") params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0)
            if (JsonObjectFindKey(jsonObject, "alignParentEnd") != null && JsonObjectFindKey(jsonObject, "alignParentEnd") == "true") params.addRule(RelativeLayout.ALIGN_PARENT_END)
            if (JsonObjectFindKey(jsonObject, "alignParentEnd") != null && JsonObjectFindKey(jsonObject, "alignParentEnd") == "false") params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0)
            if (JsonObjectFindKey(jsonObject, "alignParentLeft") != null && JsonObjectFindKey(jsonObject, "alignParentLeft") == "true") params.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
            if (JsonObjectFindKey(jsonObject, "alignParentLeft") != null && JsonObjectFindKey(jsonObject, "alignParentLeft") == "false") params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0)
            if (JsonObjectFindKey(jsonObject, "alignParentRight") != null && JsonObjectFindKey(jsonObject, "alignParentRight") == "true") params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            if (JsonObjectFindKey(jsonObject, "alignParentRight") != null && JsonObjectFindKey(jsonObject, "alignParentRight") == "false") params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0)
            params.setMargins(0, 50, 0, 0)
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.layoutParams = params
        } catch (e: Exception) {
            Log.e(TAG, e.message!!)
        }
    }

    fun setImageView(imageView: ImageView?, jsonObject: JSONObject) {
        try {
            val lp = imageView!!.layoutParams as LinearLayout.LayoutParams
            var marginTop = 0
            var marginLeft = 0
            var marginBottom = 0
            var marginRight = 0
            if (JsonObjectFindKey(jsonObject, "marginTop") != null) marginTop = Integer.valueOf(JsonObjectFindKey(jsonObject, "marginTop")!!)
            if (JsonObjectFindKey(jsonObject, "marginLeft") != null) marginLeft = Integer.valueOf(JsonObjectFindKey(jsonObject, "marginLeft")!!)
            if (JsonObjectFindKey(jsonObject, "marginBottom") != null) marginBottom = Integer.valueOf(JsonObjectFindKey(jsonObject, "marginBottom")!!)
            if (JsonObjectFindKey(jsonObject, "marginRight") != null) marginRight = Integer.valueOf(JsonObjectFindKey(jsonObject, "marginRight")!!)
            lp.setMargins(marginLeft, marginTop, marginRight, marginBottom)
            imageView.layoutParams = lp
        } catch (e: Exception) {
            Log.e(TAG, e.message!!)
        }
    }

    fun JsonObjectFindKey(jsonObject: JSONObject, key: String?): String? {
        return try {
            var value: String? = null
            if (jsonObject.getString(key).isNotEmpty()) value = jsonObject.getString(key)
            value
        } catch (e: JSONException) {
            Log.e(TAG, e.message!!)
            null
        }
    }
    companion object {
        private const val TAG = "RemoteConfigActivity"
        @SuppressLint("StaticFieldLeak")
        private var imageView_one_logo: ImageView? = null
        private var imageView_two_logo: ImageView? = null
        private var imageView_three_logo: ImageView? = null
        private var imageView_four_logo: ImageView? = null
        private var imageView_five_logo: ImageView? = null
        private var imageView_six_logo: ImageView? = null
        private var sns_layout: LinearLayout? = null
        private var textView2: TextView? = null
        fun initialize() {
            val remoteConfig = FirebaseRemoteConfig.getInstance()
            val configSettings = FirebaseRemoteConfigSettings.Builder()
                    .setDeveloperModeEnabled(true)
                    .build()
            remoteConfig.setConfigSettings(configSettings)
            remoteConfig.setDefaults(R.xml.remote_config_defaults)
            remoteConfig.fetch(1)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            remoteConfig.activateFetched()
                            Log.d(TAG, "Fetch and activate succeeded")
                        } else Log.d(TAG, "Fetch failed")
                        val hh = "하이"
                    }
        }

        fun getConfigValue(key: String?): String {
            val remoteConfig = FirebaseRemoteConfig.getInstance()
            return remoteConfig.getString(key)
        }
    }
}