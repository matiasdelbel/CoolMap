package com.mdelbel.android.coolmap.data.location

import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.mdelbel.android.domain.location.LocationNoFounded
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.mockito.Mockito.mock

//TODO terminar
class GoogleLocationRequesterTest {

    @Test
    fun `request location with last location not founded should returns location not founded`() {
        // mock google client
        val locationClient = mock(FusedLocationProviderClient::class.java)
        val taskObtainLocation = mock(Task::class.java) as Task<Location>
        whenever(locationClient.lastLocation).thenReturn(taskObtainLocation)
        whenever(taskObtainLocation.addOnSuccessListener(any<OnSuccessListener<Location>>())).thenAnswer(null)
        // mock geo coder
        val geoCoder = mock(Geocoder::class.java)
        val requester = GoogleLocationRequester(locationClient, geoCoder)

        requester.requestLocation().subscribe {
            assert(it is LocationNoFounded)
        }
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