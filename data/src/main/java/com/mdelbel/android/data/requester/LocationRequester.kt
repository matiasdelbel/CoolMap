package com.mdelbel.android.data.requester

import com.mdelbel.android.domain.location.Location
import io.reactivex.Observable

interface LocationRequester {

    fun requestLocation(): Observable<Location>
}