package com.mdelbel.android.coolmap.data.location

import android.location.Address
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.mdelbel.android.data.requester.LocationRequester
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.Country
import io.reactivex.Single
import io.reactivex.SingleEmitter
import android.location.Location as AndroidLocation

class GoogleLocationRequester(
    private val locationClient: FusedLocationProviderClient, private val geoCoder: Geocoder
) : LocationRequester {

    private lateinit var resultEmitter: SingleEmitter<LocationOnCountry>

    @Suppress("MissingPermission")
    override fun requestLocation(): Single<LocationOnCountry> {
        val requestResult = Single.create<LocationOnCountry> { resultEmitter = it }
        locationClient.lastLocation.addOnSuccessListener { location: AndroidLocation? ->
            when {
                isNotFounded(location) -> publishNotFound()
                else -> publishFound(location!!)
            }
        }

        return requestResult
    }

    private fun isNotFounded(location: AndroidLocation?) = location == null

    private fun publishFound(location: AndroidLocation) {
        val selected = obtainLocationFrom(location)
        when (selected) {
            null -> publishNotFound()
            else -> publish(selected)
        }
    }

    private fun obtainLocationFrom(location: AndroidLocation): Address? {
        val addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
        return addresses.firstOrNull()
    }

    private fun publishNotFound() {
        resultEmitter.onError(IllegalStateException("Cannot find obtain current location."))
    }

    private fun publish(address: Address) {
        val location = Location(address.latitude, address.longitude)
        val country = Country(address.countryCode, address.countryName)

        resultEmitter.onSuccess(LocationOnCountry(location, country))
    }
}