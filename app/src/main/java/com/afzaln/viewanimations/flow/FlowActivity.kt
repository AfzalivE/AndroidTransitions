package com.afzaln.viewanimations.flow

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afzaln.viewanimations.R
import flow.Flow

class FlowActivity : AppCompatActivity() {

    override fun attachBaseContext(baseContext: Context) {
        val flowContextWrapper = Flow.configure(baseContext, this)
            .dispatcher(AppDispatcher(this))
            .defaultKey(HomeScreen())
            .keyParceler(TransitionAppKeyParceler())
            .install()
        super.attachBaseContext(flowContextWrapper)
    }

    override fun onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
    }
}