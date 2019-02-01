package com.mdelbel.android.domain.place

object NoCityInfo : CityInfo(city = NonExistentCity, language = EMPTY_FIELDS, currency = EMPTY_FIELDS)

private const val EMPTY_FIELDS = ""