package com.mdelbel.android.coolmap.data.place

import com.google.maps.android.PolyUtil
import com.mdelbel.android.domain.place.Area
import com.mdelbel.android.domain.place.City
import com.mdelbel.android.domain.place.Location
import com.mdelbel.android.domain.place.WorkingArea

/**
 *
 *  {
 *      "code": "BUE",
 *      "name": "Buenos Aires",
 *      "currency": "ARS",
 *      "country_code": "AR",
 *      "enabled": true,
 *      "time_zone": "America/Argentina/Buenos_Aires",
 *      "working_area": [
 *          "vbcrErb|cJwaCho@elDarEhy@wlBlRrVnvArlCpfAfQ",
 *          "rnbrEjh{cJpUtW}Fp@",
 *      ],
 *      "busy": false,
 *      "language_code": "es"
 *  }
 *
 */
data class CityDto(
    val code: String,
    val name: String,
    val country_code: String,
    val language_code: String,
    val currency: String,
    val working_area: List<String>
) {

    fun asCity(): City {
        val workingArea = mutableListOf<Area>()
        working_area.forEach { encodedArea ->
            val areas = mutableListOf<Location>()
            val areaAsLatLngPoints = PolyUtil.decode(encodedArea)
            areaAsLatLngPoints.forEach { areas.add(Location(it.latitude, it.longitude)) }
            workingArea.add(Area(areas))
        }

        return City(code, name, country_code, language_code, currency, WorkingArea(workingArea))
    }
}