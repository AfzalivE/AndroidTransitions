package com.afzaln.viewanimations.flow

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.afzaln.viewanimations.R
import flow.*
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_flow.*

class AppDispatcher(private val activity: Activity) : Dispatcher {
    override fun dispatch(traversal: Traversal, callback: TraversalCallback) {
        val transitionDispatcher = TransitionDispatcher()
        val destination = traversal.destination.top<Any>()

        val layoutRes = when (destination) {
            is HomeScreen  -> R.layout.screen_home
            is AlarmScreen -> R.layout.screen_alarm
            else           -> throw IllegalStateException("Unknown screen $destination")
        }

        val newView = LayoutInflater.from(traversal.createContext(destination, activity))
            .inflate(layoutRes, activity.container, false) as ViewGroup

        if (traversal.origin != null && activity.container.childCount > 0) {
            val currentView = activity.container.getChildAt(0) as ViewGroup
            // Save the state
            traversal.getState((traversal.origin as History).top()).save(currentView)
            // Restore state before adding view (i.e. caused by onBackPressed)
            traversal.getState(traversal.destination.top()).restore(newView)

            transitionDispatcher.applyTransition(activity.container, currentView, newView)
        } else {
            // Restore state before adding view (i.e. caused by onBackPressed)
            traversal.getState(traversal.destination.top()).restore(newView)

            activity.container.removeAllViews()

            // add new screen
            activity.container.addView(newView)
        }


        callback.onTraversalCompleted() // Tell Flow that we are done
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    class HomeScreen : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    class AlarmScreen : Parcelable

    class TransitionAppKeyParceler : KeyParceler {
        override fun toParcelable(key: Any): Parcelable = key as Parcelable
        override fun toKey(parcelable: Parcelable): Any = parcelable

    }
}