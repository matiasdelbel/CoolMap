package com.mdelbel.android.usecases.permissions

import com.mdelbel.android.data.requester.PermissionsRequester
import javax.inject.Inject

class AskLocationPermissions @Inject constructor(private val permissionsRequester: PermissionsRequester) {

    operator fun invoke() = permissionsRequester.requestLocationPermissions()
}