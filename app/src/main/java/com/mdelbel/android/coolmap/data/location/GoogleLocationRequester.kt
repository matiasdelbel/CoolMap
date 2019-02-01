package com.mdelbel.android.coolmap.data.location

import android.location.Address
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.mdelbel.android.data.requester.LocationRequester
import com.mdelbel.android.domain.location.UserLocation
import com.mdelbel.android.domain.place.Country
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import android.location.Location as AndroidLocation

// FIX: wrap Google dependency on a helper class
class GoogleLocationRequester(
    private val locationClient: FusedLocationProviderClient,
    private val geoCoder: Geocoder
) : LocationRequester {

    private lateinit var resultEmitter: ObservableEmitter<UserLocation>

    @Suppress("MissingPermission")
    override fun requestLocation(): Observable<UserLocation> {
        val requestResult = Observable.create<UserLocation> { resultEmitter = it }
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
        resultEmitter.onComplete()
    }

    private fun publish(location: Address) {
        resultEmitter.onNext(
            UserLocation(location.latitude, location.longitude, Country(location.countryCode, location.countryName))
        )
        resultEmitter.onComplete()
    }
}