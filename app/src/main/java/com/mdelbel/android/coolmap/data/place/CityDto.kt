package com.mdelbel.android.coolmap.data.place

import com.google.maps.android.PolyUtil
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.place.Area
import com.mdelbel.android.domain.place.City
import com.mdelbel.android.domain.place.WorkingArea

/**
 *
 * { working_area": [],
 *   "code": "CAI",
 *    "name": "Cairo",
 *    "country_code": "EG"
 * }
 *
 */
data class CityDto(val code: String, val name: String, val country_code: String, val working_area: List<String>) {

    fun asCity(): City {
        val workingArea = mutableListOf<Area>()
        working_area.forEach { encodedArea ->
            val areas = mutableListOf<Location>()
            val areaAsLatLngPoints = PolyUtil.decode(encodedArea)
            areaAsLatLngPoints.forEach {
                areas.add(Location(latitude = it.latitude, longitude = it.longitude))
            }
            workingArea.add(Area(areas))
        }
        return City(code = code, name = name, countryCode = country_code, workingArea = WorkingArea(workingArea))
    }
}