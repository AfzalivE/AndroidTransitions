package com.afzaln.viewanimations

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.view.Window
import kotlinx.android.synthetic.main.activity_home.*


class HomeSharedActivity : AppCompatActivity() {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // inside your activity (if you did not enable transitions in your theme)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(R.layout.activity_home)

        home_fab.setOnClickListener({
            val intent = Intent(this, AlarmActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, home_fab, "fab")

            // set an exit transition
            window.exitTransition = Fade()

            startActivity(intent, options.toBundle())
        })
    }
}