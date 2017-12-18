package com.afzaln.viewanimations

import android.os.Bundle
import android.support.transition.*
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_alarm.view.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val alarmView = layoutInflater.inflate(R.layout.activity_alarm, null)

        val transition = TransitionSet()
            .addTransition(Slide(Gravity.BOTTOM)
                .addTarget(alarmView.alarmView)
                .setDuration(500)
                .setInterpolator(LinearOutSlowInInterpolator()))
            .addTransition(ChangeBounds()
                .addTarget(homeView.homeFab)
                .addTarget(alarmView.alarmFab))
            .addListener(object : TransitionListenerAdapter() {
                override fun onTransitionEnd(transition: Transition) {
                    viewGroup.removeView(homeView)
                }
            })

        homeFab.setOnClickListener({
            TransitionManager.beginDelayedTransition(viewGroup, transition)
            viewGroup.addView(alarmView)
            homeView.removeView(homeView.homeFab)
        })
    }
}