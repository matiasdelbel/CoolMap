package com.mdelbel.android.data.requester

import com.mdelbel.android.domain.permissions.PermissionsResult
import io.reactivex.Observable

interface PermissionsRequester {

    fun requestLocationPermissions(): Observable<PermissionsResult>
}