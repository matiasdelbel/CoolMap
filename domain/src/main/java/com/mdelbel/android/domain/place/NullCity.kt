package com.mdelbel.android.domain.place

object NullCity : CityDetail() {

    override fun invokeIfContain(locationToCheck: Location, ifContain: () -> Unit, ifNotContain: () -> Unit) {
        ifNotContain()
    }

    override fun invokeIfFrom(country: Country, ifIsFrom: () -> Unit, ifIsNotFrom: () -> Unit) {
        ifIsNotFrom()
    }
}