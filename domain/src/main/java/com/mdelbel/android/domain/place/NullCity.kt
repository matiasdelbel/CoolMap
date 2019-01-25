package com.mdelbel.android.domain.place

import com.mdelbel.android.domain.location.UserLocation

object NullCity : CityDetail() {

    override fun invokeIfContain(locationToCheck: UserLocation, ifContain: () -> Unit, ifNotContain: () -> Unit) {
        ifNotContain()
    }
}