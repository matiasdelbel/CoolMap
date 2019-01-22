package com.mdelbel.android.usecases.location

import com.mdelbel.android.data.requester.LocationRequester

class ObtainLocation(private val locationRequester: LocationRequester) {

    operator fun invoke() = locationRequester.requestLocation()
}