package com.mdelbel.android.domain.place

import com.mdelbel.android.domain.location.Location

open  class City(val code: String, val name: String, val countryCode: String, val workingArea: WorkingArea) {

    fun contain(location: Location) {
        //TODO
    }

}