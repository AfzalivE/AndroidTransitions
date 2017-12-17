package com.afzaln.viewanimations

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.transition.TransitionManager
import android.view.View
import kotlinx.android.synthetic.main.activity_colorful.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colorful)

        root.postDelayed({
            TransitionManager.beginDelayedTransition(root)
            blue.visibility = View.VISIBLE
        }, 1000)
    }
}
