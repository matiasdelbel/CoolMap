package com.mdelbel.android.coolmap.data.place

import com.google.maps.android.PolyUtil
import com.mdelbel.android.domain.place.city.area.Area
import com.mdelbel.android.domain.place.city.City
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.place.city.area.WorkingArea

/**
 *
 * { working_area": [
 *      "tanxBp`_kG"
 *   ],
 *   "code": "CAI",
 *   "name": "Cairo",
 *   "country_code": "EG"
 * }
 *
 */
data class CityDetailDto(val code: String, val name: String, val country_code: String, val working_area: List<String>) {

    fun asCity(): City {
        val workingArea = mutableListOf<Area>()
        working_area.forEach { encodedArea ->
            val areas = mutableListOf<Location>()
            val areaAsLatLngPoints = PolyUtil.decode(encodedArea)
            areaAsLatLngPoints.forEach { areas.add(
                Location(
                    it.latitude,
                    it.longitude
                )
            ) }

            if (areas.isNotEmpty()) {
                workingArea.add(Area(areas))
            }
        }

        return City(code, name, country_code, WorkingArea(workingArea))
    }
}