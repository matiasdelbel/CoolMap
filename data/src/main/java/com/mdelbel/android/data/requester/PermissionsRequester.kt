package com.mdelbel.android.data.requester

import com.mdelbel.android.domain.permissions.PermissionsResult
import io.reactivex.Single

interface PermissionsRequester {

    fun requestLocationPermissions(): Single<PermissionsResult>
}