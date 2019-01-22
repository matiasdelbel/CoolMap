package com.mdelbel.android.coolmap.data.permissions

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.mdelbel.android.domain.permissions.PermissionsDenied
import com.mdelbel.android.domain.permissions.PermissionsGranted
import com.mdelbel.android.usecases.permissions.AskLocationPermissions

class RequesterActivityBinder(
    private val permissionsRequester: MapPermissionsRequester,
    private val requesterActivity: AppCompatActivity
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connect() {
        permissionsRequester.attach(requesterActivity)

        //TODO move deberia haber un fragment con su view model onda LocationChooser
        AskLocationPermissions(permissionsRequester)().subscribe {
            if (it is PermissionsGranted) {
                Toast.makeText(requesterActivity, "Otorgados", Toast.LENGTH_LONG).show()
            } else if (it is PermissionsDenied) {
                Toast.makeText(requesterActivity, "Denegados", Toast.LENGTH_LONG).show()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnect() {
        permissionsRequester.detachActivity()
    }
}