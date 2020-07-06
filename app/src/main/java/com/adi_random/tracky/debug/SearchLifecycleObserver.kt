package com.adi_random.tracky.debug

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by Adrian Pascu on 06-Jul-20.
 */
class SearchLifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun activityPaused() {
        Log.d("LifecycleObserver", "Search Activity got paused")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityDestroyed() {
        Log.d("LifecycleObserver", "Search Activity stopped")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStarted() {
        Log.d("LifecycleObserver", "Search Activity started")
    }
}