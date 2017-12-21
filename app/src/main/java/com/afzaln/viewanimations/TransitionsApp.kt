package com.afzaln.viewanimations

import android.app.Application

import timber.log.Timber

/**
 * Created by afzal on 2017-12-20.
 */
class TransitionsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
