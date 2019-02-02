package com.mdelbel.android.coolmap.data.place.dto

import com.mdelbel.android.domain.place.city.City

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
data class CityDto(val code: String, val name: String, val country_code: String, val working_area: List<String>) {

    fun asCity(workingAreaDecoder: WorkingAreaDecoder = WorkingAreaDecoder) =
        City(code, name, country_code, workingAreaDecoder.decode(working_area))
}