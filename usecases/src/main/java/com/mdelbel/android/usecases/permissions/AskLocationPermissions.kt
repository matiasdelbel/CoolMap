package com.mdelbel.android.usecases.permissions

import com.mdelbel.android.data.permissions.PermissionsRequester

class AskLocationPermissions(private val permissionsRequester: PermissionsRequester) {

    operator fun invoke() = permissionsRequester.requestLocationPermissions()

}