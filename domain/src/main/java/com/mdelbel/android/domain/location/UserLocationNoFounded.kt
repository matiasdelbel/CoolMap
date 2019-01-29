package com.mdelbel.android.domain.location

import com.mdelbel.android.domain.place.Country

object UserLocationNoFounded : UserLocation(NO_LATITUDE, NO_LONGITUDE, EMPTY_COUTRY)

private const val NO_LATITUDE = 0.0
private const val NO_LONGITUDE = 0.0
private val EMPTY_COUTRY = Country("", "")