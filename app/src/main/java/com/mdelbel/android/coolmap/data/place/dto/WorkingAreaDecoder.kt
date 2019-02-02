package com.mdelbel.android.coolmap.data.place.dto

import com.google.maps.android.PolyUtil
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.place.city.area.Area
import com.mdelbel.android.domain.place.city.area.WorkingArea

object WorkingAreaDecoder {

    fun decode(encodedWorkingArea: List<String>): WorkingArea {
        val workingArea = mutableListOf<Area>()
        encodedWorkingArea.forEach { encodedArea ->
            val area = decode(encodedArea)
            if (area != null) {
                workingArea.add(area)
            }
        }
        return WorkingArea(workingArea)
    }

    private fun decode(encodedArea: String): Area? {
        val areas = mutableListOf<Location>()
        val areaAsLatLngPoints = PolyUtil.decode(encodedArea)
        areaAsLatLngPoints.forEach { areas.add(Location(it.latitude, it.longitude)) }

        return when {
            areas.isNotEmpty() -> Area(areas)
            else -> null
        }
    }
}