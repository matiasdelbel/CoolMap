package com.mdelbel.android.data.requester

import com.mdelbel.android.domain.location.LocationOnCountry
import io.reactivex.Observable

interface LocationRequester {

    fun requestLocation(): Observable<LocationOnCountry>
}