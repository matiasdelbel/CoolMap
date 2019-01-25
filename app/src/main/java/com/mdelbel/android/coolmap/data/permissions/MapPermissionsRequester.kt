package com.mdelbel.android.coolmap.data.permissions

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission
import com.mdelbel.android.data.requester.PermissionsRequester
import com.mdelbel.android.domain.permissions.PermissionsDenied
import com.mdelbel.android.domain.permissions.PermissionsGranted
import com.mdelbel.android.domain.permissions.PermissionsResult
import io.reactivex.Single
import io.reactivex.SingleEmitter

class MapPermissionsRequester : PermissionsRequester {

    private var requesterActivity: AppCompatActivity? = null
    private lateinit var resultEmitter: SingleEmitter<PermissionsResult>

    override fun requestLocationPermissions(): Single<PermissionsResult> = Single.create<PermissionsResult> {
        resultEmitter = it
        when {
            shouldAskForPermission() -> askForPermission()
            else -> publishResult(PermissionsGranted)
        }
    }

    internal fun attach(requesterActivity: AppCompatActivity) {
        this.requesterActivity = requesterActivity
    }

    internal fun detachActivity() {
        this.requesterActivity = null
    }

    internal fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == PERMISSIONS_REQUEST_LOCATION) {
            when {
                arePermissionGranted(grantResults) -> publishResult(PermissionsGranted)
                else -> publishResult(PermissionsDenied)
            }
        }
    }

    private fun publishResult(permissionsResult: PermissionsResult) = resultEmitter.onSuccess(permissionsResult)

    private fun shouldAskForPermission() =
        checkSelfPermission(requesterActivity!!, LOCATION_PERMISSION) != PERMISSION_GRANTED

    private fun askForPermission() =
        requestPermissions(requesterActivity!!, arrayOf(LOCATION_PERMISSION), PERMISSIONS_REQUEST_LOCATION)

    private fun arePermissionGranted(grantResults: IntArray) =
        grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED
}

private const val PERMISSIONS_REQUEST_LOCATION = 1605
private const val LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION