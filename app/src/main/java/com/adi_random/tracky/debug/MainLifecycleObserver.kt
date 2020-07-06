package com.adi_random.tracky.debug

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by Adrian Pascu on 06-Jul-20.
 */
class MainLifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun activityPaused() {
        Log.d("LifecycleObserver", "Main Activity got paused")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityDestroyed() {
        Log.d("LifecycleObserver", "Main Activity stopped")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStarted() {
        Log.d("LifecycleObserver", "Main Activity started")
    }
}