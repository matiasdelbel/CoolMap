package com.mdelbel.android.domain.location

object LocationNoFounded : Location(
    latitude = NO_LATITUDE,
    longitude = NO_LONGITUDE,
    city = EMPTY_CITY_NAME,
    country = EMPTY_COUNTRY_NAME
)

private const val NO_LATITUDE = 0.0
private const val NO_LONGITUDE = 0.0
private const val EMPTY_CITY_NAME = ""
private const val EMPTY_COUNTRY_NAME = ""