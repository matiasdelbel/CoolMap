package com.mdelbel.android.data.requester

import com.mdelbel.android.domain.location.UserLocation
import io.reactivex.Observable

interface LocationRequester {

    fun requestLocation(): Observable<UserLocation>
}