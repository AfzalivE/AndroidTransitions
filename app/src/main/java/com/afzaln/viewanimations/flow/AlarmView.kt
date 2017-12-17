package com.afzaln.viewanimations.flow

import android.content.Context
import android.support.constraint.ConstraintSet
import android.support.transition.ChangeBounds
import android.support.transition.Fade
import android.support.transition.TransitionManager
import android.support.transition.TransitionSet
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.afzaln.viewanimations.R
import kotlinx.android.synthetic.main.screen_alarm.view.*

class AlarmView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val idleSet = ConstraintSet()
        val triggeredSet = ConstraintSet()

        idleSet.clone(alarmView)
        with(triggeredSet) {
            clone(alarmView)

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
            TransitionManager.beginDelayedTransition(alarmView, transition)
            triggeredSet.applyTo(alarmView)
        })

        okButton.setOnClickListener({
            TransitionManager.beginDelayedTransition(alarmView, transition)
            idleSet.applyTo(alarmView)
        })
    }
}