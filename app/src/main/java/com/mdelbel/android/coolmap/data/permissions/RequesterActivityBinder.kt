package com.mdelbel.android.coolmap.data.permissions

import android.location.Geocoder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.location.LocationServices
import com.mdelbel.android.coolmap.data.location.GoogleLocationRequester
import com.mdelbel.android.domain.permissions.PermissionsDenied
import com.mdelbel.android.domain.permissions.PermissionsGranted
import com.mdelbel.android.domain.permissions.PermissionsResult
import com.mdelbel.android.usecases.location.ObtainLocation
import com.mdelbel.android.usecases.permissions.AskLocationPermissions

class RequesterActivityBinder(
    private val permissionsRequester: MapPermissionsRequester,
    private val requesterActivity: AppCompatActivity
) : LifecycleObserver {

    private var permissionStatus: PermissionsResult? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connect() {
        permissionsRequester.attach(requesterActivity)

        //TODO move deberia haber un fragment con su view model onda LocationChooser
        if (permissionStatus == null) {
            AskLocationPermissions(permissionsRequester)().subscribe {
                permissionStatus = it
                if (it is PermissionsGranted) {
                    Toast.makeText(requesterActivity, "Otorgados", Toast.LENGTH_LONG).show()
                    requestLocation()
                } else if (it is PermissionsDenied) {
                    Toast.makeText(requesterActivity, "Denegados", Toast.LENGTH_LONG).show()
                }
            } //TODO esta crasheando deberia ser por el dispose?
        } else {
            requestLocation()
        }

    }

    //TODO mooove
    private fun requestLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requesterActivity.applicationContext)
        ObtainLocation(GoogleLocationRequester(fusedLocationClient, Geocoder(requesterActivity.applicationContext)))()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnect() {
        permissionsRequester.detachActivity()
    }
}