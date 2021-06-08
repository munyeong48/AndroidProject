package com.goldenplanet.www.gp_android;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MoveActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        Intent intent = getIntent();
        String cc = intent.getStringExtra("cc");
        String cn = intent.getStringExtra("cn");
        String cm = intent.getStringExtra("cm");
        String cs = intent.getStringExtra("cs");
        String ck = intent.getStringExtra("ck");
        String ci = intent.getStringExtra("ci");
        String anid = intent.getStringExtra("anid");
        String gclid = intent.getStringExtra("gclid");
        String dclid = intent.getStringExtra("dclid");
        String aclid = intent.getStringExtra("aclid");
        String gmob_t = intent.getStringExtra("gmob_t");


        
    }
}
