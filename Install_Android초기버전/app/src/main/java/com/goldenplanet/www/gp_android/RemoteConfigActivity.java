package com.goldenplanet.www.gp_android;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RemoteConfigActivity extends AppCompatActivity {

    private static final String TAG = "RemoteConfigActivity";
    private static ImageView imageView_one_logo ;
    private static ImageView imageView_two_logo;
    private static ImageView imageView_three_logo;
    private static ImageView imageView_four_logo;
    private static ImageView imageView_five_logo;
    private static ImageView imageView_six_logo;
    private static LinearLayout sns_layout;
    private static TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_config);


        EditText editText2 = findViewById(R.id.editText2);
        imageView_one_logo = findViewById(R.id.imageView_one_logo);
        imageView_two_logo = findViewById(R.id.imageView_two_logo);
        imageView_three_logo = findViewById(R.id.imageView_three_logo);
        imageView_four_logo = findViewById(R.id.imageView_four_logo);
        imageView_five_logo = findViewById(R.id.imageView_five_logo);
        imageView_six_logo = findViewById(R.id.imageView_six_logo);

        btnSetOnClickListener onClickListener = new btnSetOnClickListener();
        imageView_one_logo.setOnClickListener(onClickListener);
        imageView_two_logo.setOnClickListener(onClickListener);
        imageView_three_logo.setOnClickListener(onClickListener);
        imageView_four_logo.setOnClickListener(onClickListener);
        imageView_five_logo.setOnClickListener(onClickListener);
        imageView_six_logo.setOnClickListener(onClickListener);

        sns_layout = findViewById(R.id.sns_layout);
        textView2 = findViewById(R.id.textView2);
        String value = getConfigValue("json_layout");
        String title = getConfigValue("TEST");


        try {
            JSONArray jsonArray = new JSONArray(value);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);

                if(explrObject.getString("id").contains("text")){
                    if("textView2".equals(explrObject.getString("id"))) setTextView(textView2, explrObject);
                } else if (explrObject.getString("id").contains("layout")){
                    if("sns_layout".equals(explrObject.getString("id"))) setLinearLayout(sns_layout, explrObject);
                } else if (explrObject.getString("id").contains("image")){
                    if("imageView_one_logo".equals(explrObject.getString("id"))) setImageView(imageView_one_logo, explrObject);
                    if("imageView_two_logo".equals(explrObject.getString("id"))) setImageView(imageView_two_logo, explrObject);
                    if("imageView_three_logo".equals(explrObject.getString("id"))) setImageView(imageView_three_logo, explrObject);
                    if("imageView_four_logo".equals(explrObject.getString("id"))) setImageView(imageView_four_logo, explrObject);
                    if("imageView_five_logo".equals(explrObject.getString("id"))) setImageView(imageView_five_logo, explrObject);
                    if("imageView_six_logo".equals(explrObject.getString("id"))) setImageView(imageView_six_logo, explrObject);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    public class btnSetOnClickListener implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageView_one_logo:
                    break;
                case R.id.imageView_two_logo:
                    break;
                case R.id.imageView_three_logo:
                    break;
                case R.id.imageView_four_logo:
                    break;
                case R.id.imageView_five_logo:
                    break;
                case R.id.imageView_six_logo:
                    Bundle params = new Bundle();
                    FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(RemoteConfigActivity.this);
                    params.putString("여섯번째이벤트눌렀다", "아자" + TAG);
                    mFirebaseAnalytics.logEvent("custom_event", params);
                    break;

                case R.id.subscribeButton:
                    break;
            }
        }
    }
    public void setTextView(TextView textView, JSONObject jsonObject){
        try {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)textView.getLayoutParams();

            Integer marginTop = 0;
            Integer marginLeft = 0;
            Integer marginBottom = 0;
            Integer marginRight = 0;

            if(JsonObjectFindKey(jsonObject,"marginTop") != null) marginTop = Integer.valueOf(JsonObjectFindKey(jsonObject,"marginTop"));
            if(JsonObjectFindKey(jsonObject,"marginLeft") != null) marginLeft = Integer.valueOf(JsonObjectFindKey(jsonObject,"marginLeft"));
            if(JsonObjectFindKey(jsonObject,"marginBottom") != null) marginBottom = Integer.valueOf(JsonObjectFindKey(jsonObject,"marginBottom"));
            if(JsonObjectFindKey(jsonObject,"marginRight") != null) marginRight = Integer.valueOf(JsonObjectFindKey(jsonObject,"marginRight"));

            lp.setMargins(marginLeft, marginTop, marginRight,marginBottom);
            textView.setLayoutParams(lp);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void setLinearLayout(LinearLayout linearLayout, JSONObject jsonObject) {
        try {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)linearLayout.getLayoutParams();

            if (JsonObjectFindKey(jsonObject, "alignParentBottom") != null && JsonObjectFindKey(jsonObject, "alignParentBottom").equals("true"))
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            if (JsonObjectFindKey(jsonObject, "alignParentBottom") != null && JsonObjectFindKey(jsonObject, "alignParentBottom").equals("false"))
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,0);

            if (JsonObjectFindKey(jsonObject, "alignParentEnd") != null && JsonObjectFindKey(jsonObject, "alignParentEnd").equals("true"))
                params.addRule(RelativeLayout.ALIGN_PARENT_END);
            if (JsonObjectFindKey(jsonObject, "alignParentEnd") != null && JsonObjectFindKey(jsonObject, "alignParentEnd").equals("false"))
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,0);

            if (JsonObjectFindKey(jsonObject, "alignParentLeft") != null && JsonObjectFindKey(jsonObject, "alignParentLeft").equals("true"))
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            if (JsonObjectFindKey(jsonObject, "alignParentLeft") != null && JsonObjectFindKey(jsonObject, "alignParentLeft").equals("false"))
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,0);

            if (JsonObjectFindKey(jsonObject, "alignParentRight") != null && JsonObjectFindKey(jsonObject, "alignParentRight").equals("true"))
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            if (JsonObjectFindKey(jsonObject, "alignParentRight") != null && JsonObjectFindKey(jsonObject, "alignParentRight").equals("false"))
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,0);
            params.setMargins(0,50,0,0);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(params);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void setImageView(ImageView imageView, JSONObject jsonObject){
        try {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)imageView.getLayoutParams();

            Integer marginTop = 0;
            Integer marginLeft = 0;
            Integer marginBottom = 0;
            Integer marginRight = 0;

            if(JsonObjectFindKey(jsonObject,"marginTop") != null) marginTop = Integer.valueOf(JsonObjectFindKey(jsonObject,"marginTop"));
            if(JsonObjectFindKey(jsonObject,"marginLeft") != null) marginLeft = Integer.valueOf(JsonObjectFindKey(jsonObject,"marginLeft"));
            if(JsonObjectFindKey(jsonObject,"marginBottom") != null) marginBottom = Integer.valueOf(JsonObjectFindKey(jsonObject,"marginBottom"));
            if(JsonObjectFindKey(jsonObject,"marginRight") != null) marginRight = Integer.valueOf(JsonObjectFindKey(jsonObject,"marginRight"));

            lp.setMargins(marginLeft, marginTop, marginRight,marginBottom);
            imageView.setLayoutParams(lp);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public String JsonObjectFindKey(JSONObject jsonObject, String key){
        try {
            String value = null;
            if(jsonObject.getString(key) != null) value = jsonObject.getString(key);
            return value;
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

    }

    public static void initialize(){
        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(true)
                .build();
        remoteConfig.setConfigSettings(configSettings);
        remoteConfig.setDefaults(R.xml.remote_config_defaults);
        remoteConfig.fetch(1)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            remoteConfig.activateFetched();
                            Log.d(TAG,"Fetch and activate succeeded");
                        }else
                            Log.d(TAG,"Fetch failed");
                        String hh = "하이";

                    }
                });
    }

    public static String getConfigValue(String key){
        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        return remoteConfig.getString(key);
    }
}
