package com.mdelbel.android.coolmap.data.permissions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class RequesterActivityBinder(
    private val permissionsRequester: MapPermissionsRequester,
    private val requesterActivity: AppCompatActivity
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun connect() {
        permissionsRequester.attach(requesterActivity)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun disconnect() {
        permissionsRequester.detachActivity()
    }
}