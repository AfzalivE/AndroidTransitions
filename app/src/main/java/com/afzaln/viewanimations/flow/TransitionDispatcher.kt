package com.afzaln.viewanimations.flow

import android.support.transition.*
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.Gravity
import android.view.ViewGroup
import kotlinx.android.synthetic.main.screen_alarm.view.*
import kotlinx.android.synthetic.main.screen_home.view.*

class TransitionDispatcher {

    fun applyTransition(rootView: ViewGroup, currentView: ViewGroup, newView: ViewGroup) {
        if (currentView is HomeView && newView is AlarmView) {
            val transition = TransitionSet()
                .addTransition(Slide(Gravity.BOTTOM)
                    .addTarget(newView.whiteBackground))
                .addTransition(Slide(Gravity.BOTTOM)
                    .addTarget(newView.alarmView)
                    .setDuration(500)
                    .setInterpolator(LinearOutSlowInInterpolator()))
                .addTransition(ChangeBounds()
                    .addTarget(currentView.homeFab)
                    .addTarget(newView.alarmFab))
                .addListener(object : TransitionListenerAdapter() {
                    override fun onTransitionEnd(transition: Transition) = rootView.removeView(currentView)
                    override fun onTransitionCancel(transition: Transition) = rootView.removeView(newView)
                })

            TransitionManager.beginDelayedTransition(rootView, transition)
            rootView.addView(newView)
            currentView.removeView(currentView.homeFab)
        } else if (currentView is AlarmView && newView is HomeView) {
            val transition = TransitionSet()
                .addTransition(Slide(Gravity.BOTTOM)
                    .addTarget(currentView.alarmView)
                    .setDuration(500)
                    .setInterpolator(FastOutSlowInInterpolator()))
                .addTransition(ChangeBounds()
                    .addTarget(newView.homeFab)
                    .addTarget(currentView.alarmFab)
                    .setDuration(2000))
                .addListener(object : TransitionListenerAdapter() {
                    override fun onTransitionEnd(transition: Transition) = rootView.removeView(currentView)
                })

            TransitionManager.beginDelayedTransition(rootView, transition)
//            currentView.whiteBackground.visibility = View.GONE

            rootView.addView(newView)
            currentView.removeView(currentView.alarmFab)
            currentView.removeView(currentView.alarmView)
        } else {
            val transition = TransitionSet()
                .addTransition(AutoTransition())
                .setDuration(200)
            TransitionManager.beginDelayedTransition(rootView, transition)
            rootView.removeAllViews()
            rootView.addView(newView)
        }
    }
}