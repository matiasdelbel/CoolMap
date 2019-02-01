package com.mdelbel.android.domain.place.city

import com.google.maps.android.SphericalUtil
import com.mdelbel.android.domain.location.Location

object DistanceMeter {

    fun computeBetween(from: Location, to: Location) =
        SphericalUtil.computeDistanceBetween(from.asLatLng(), to.asLatLng())
}