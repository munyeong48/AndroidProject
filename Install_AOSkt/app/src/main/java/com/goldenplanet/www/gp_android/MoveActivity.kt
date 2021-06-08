package com.goldenplanet.Install_AOSkt

import android.app.Activity
import android.os.Bundle

class MoveActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move)
        val intent = intent
        val cc = intent.getStringExtra("cc")
        val cn = intent.getStringExtra("cn")
        val cm = intent.getStringExtra("cm")
        val cs = intent.getStringExtra("cs")
        val ck = intent.getStringExtra("ck")
        val ci = intent.getStringExtra("ci")
        val anid = intent.getStringExtra("anid")
        val gclid = intent.getStringExtra("gclid")
        val dclid = intent.getStringExtra("dclid")
        val aclid = intent.getStringExtra("aclid")
        val gmob_t = intent.getStringExtra("gmob_t")
    }
}