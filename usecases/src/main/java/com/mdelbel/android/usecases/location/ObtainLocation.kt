package com.mdelbel.android.usecases.location

import com.mdelbel.android.data.requester.LocationRequester
import javax.inject.Inject

class ObtainLocation @Inject constructor(private val locationRequester: LocationRequester) {

    operator fun invoke() = locationRequester.requestLocation()
}