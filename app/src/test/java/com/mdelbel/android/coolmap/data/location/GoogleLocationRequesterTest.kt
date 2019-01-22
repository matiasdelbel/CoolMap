package com.mdelbel.android.domain.data.location

import android.location.Geocoder
import org.junit.Test

class GoogleLocationRequesterTest {

    @Test
    fun `request location with last location not founded should returns location not founded`() {
        val locationClient = Mockito.mock(FusedLocationProviderClient::class.java)
        val geoCoder = Mockito.mock(Geocoder::class.java)
        val requester = GoogleLocationRequester(locationClient, geoCoder)

    }
}

/*
class GoogleLocationRequester(
    private val locationClient: FusedLocationProviderClient,
    private val geoCoder: Geocoder
) : LocationRequester {

    private lateinit var resultEmitter: ObservableEmitter<Location>

    @Suppress("MissingPermission")
    override fun requestLocation(): Observable<Location> {
        val requestResult = Observable.create<Location> { resultEmitter = it }
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
        val selected = obtainAddressFrom(location)
        when (selected) {
            null -> publishNotFound()
            else -> publish(selected)
        }
    }

    private fun obtainAddressFrom(location: AndroidLocation): Address? {
        val addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
        return addresses.firstOrNull()
    }

    private fun publishNotFound() {
        resultEmitter.onNext(LocationNoFounded)
    }

    private fun publish(address: Address) {
        resultEmitter.onNext(Location(address.latitude, address.longitude, address.locality, address.countryName))
    }
}
 */