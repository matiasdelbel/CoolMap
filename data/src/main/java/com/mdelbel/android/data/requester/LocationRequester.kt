package com.mdelbel.android.data.requester

import com.mdelbel.android.domain.location.LocationOnCountry
import io.reactivex.Single

interface LocationRequester {

    fun requestLocation(): Single<LocationOnCountry>
}