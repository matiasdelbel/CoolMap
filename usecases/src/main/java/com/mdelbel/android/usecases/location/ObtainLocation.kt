package com.mdelbel.android.usecases.location

import com.mdelbel.android.data.location.LocationRequester

class ObtainLocation(private val locationRequester: LocationRequester) {

    operator fun invoke() = locationRequester.requestLocation()
}