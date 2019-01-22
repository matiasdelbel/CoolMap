package com.mdelbel.android.data.location

import com.mdelbel.android.domain.location.Location
import io.reactivex.Observable

//TODO podria dividir en paquetes requester y data source
interface LocationRequester {

    fun requestLocation(): Observable<Location>
}