package com.afzaln.viewanimations.flow

import android.support.transition.*
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.Gravity
import android.view.ViewGroup
import kotlinx.android.synthetic.main.screen_alarm.view.*
import kotlinx.android.synthetic.main.screen_home.view.*

/**
 * Created by afzal on 2017-12-17.
 */
class TransitionDispatcher {

    fun applyTransition(rootView: ViewGroup, currentView: ViewGroup, newView: ViewGroup) {
        if (currentView is HomeView && newView is AlarmView) {
            val transition = TransitionSet()
                .addTransition(Slide(Gravity.BOTTOM)
                    .addTarget(newView.alarmView)
                    .setDuration(500)
                    .setInterpolator(LinearOutSlowInInterpolator()))
                .addTransition(ChangeBounds()
                    .addTarget(currentView.homeView.home_fab)
                    .addTarget(newView.alarm_fab))
                .addListener(object : TransitionListenerAdapter() {
                    override fun onTransitionEnd(transition: Transition) {
                        rootView.removeView(currentView)
                    }
                })

            TransitionManager.beginDelayedTransition(rootView, transition)
            rootView.addView(newView)
            currentView.homeView.removeView(currentView.homeView.home_fab)
        } else if (currentView is AlarmView && newView is HomeView) {
            val transition = TransitionSet()
                .addTransition(Slide(Gravity.BOTTOM)
                    .addTarget(currentView)
                    .setDuration(500)
                    .setInterpolator(FastOutSlowInInterpolator()))
                .addTransition(ChangeBounds()
                    .addTarget(currentView.alarm_fab)
                    .addTarget(newView.homeView.home_fab))
//                .addListener(object : TransitionListenerAdapter() {
//                    override fun onTransitionEnd(transition: Transition) {
//                        rootView.removeView(currentView)
//                    }
//                })

            TransitionManager.beginDelayedTransition(rootView, transition)
            rootView.removeView(currentView)
            rootView.addView(newView)
//            currentView.removeView(currentView.alarm_fab)
        } else {
            TransitionManager.beginDelayedTransition(rootView)
            rootView.removeAllViews()
            rootView.addView(newView)
        }
    }
}