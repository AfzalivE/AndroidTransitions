package com.afzaln.viewanimations

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.ChangeBounds
import android.support.transition.Fade
import android.support.transition.TransitionManager
import android.support.transition.TransitionSet
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.Window
import kotlinx.android.synthetic.main.activity_alarm.*

class AlarmActivity : AppCompatActivity() {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(R.layout.activity_alarm)

        // set an exit transition
        window.enterTransition = Slide(Gravity.BOTTOM)
            .excludeTarget(android.R.id.statusBarBackground, true)
            .excludeTarget(android.R.id.navigationBarBackground, true)


        map.onCreate(savedInstanceState)

        val idleSet = ConstraintSet()
        val triggeredSet = ConstraintSet()

        idleSet.clone(container)
        with(triggeredSet) {
            clone(container)

            setVisibility(R.id.map, View.VISIBLE)
            setVisibility(R.id.updateTitle, View.VISIBLE)
            setVisibility(R.id.titleText, View.GONE)
            setVisibility(R.id.instructions, View.GONE)
            setVisibility(R.id.helpButton, View.GONE)
            setVisibility(R.id.okButton, View.VISIBLE)
            connect(R.id.whiteBackground, ConstraintSet.TOP,
                    R.id.guideline, ConstraintSet.TOP)
        }

        val transition = TransitionSet()
            .addTransition(Fade()
                .addTarget(map)
                .addTarget(updateTitle)
                .setDuration(0))
            .addTransition(Fade()
                .addTarget(titleText)
                .addTarget(instructions)
                .setDuration(100))
            .addTransition(Fade()
                .addTarget(helpButton)
                .addTarget(okButton)
                .setDuration(400))
            .addTransition(ChangeBounds()
                .setDuration(400)
                .setInterpolator(FastOutSlowInInterpolator()))

        helpButton.setOnClickListener({
            TransitionManager.beginDelayedTransition(container, transition)
            triggeredSet.applyTo(container)
        })

        okButton.setOnClickListener({
            TransitionManager.beginDelayedTransition(container, transition)
            idleSet.applyTo(container)
        })
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map.onLowMemory()
    }
}