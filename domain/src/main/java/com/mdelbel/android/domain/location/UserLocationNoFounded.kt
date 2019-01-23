package com.mdelbel.android.domain.location

object UserLocationNoFounded : UserLocation(NO_LATITUDE, NO_LONGITUDE, EMPTY_CITY_NAME, EMPTY_COUNTRY_NAME)

private const val NO_LATITUDE = 0.0
private const val NO_LONGITUDE = 0.0
private const val EMPTY_CITY_NAME = ""
private const val EMPTY_COUNTRY_NAME = ""