package com.mdelbel.android.coolmap.data.place.dto

import com.mdelbel.android.domain.place.city.City
import com.mdelbel.android.domain.place.city.CityInfo

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
data class CityInfoDto(
    val code: String,
    val name: String,
    val country_code: String,
    val language_code: String,
    val currency: String,
    val working_area: List<String>
) {

    fun asCity(workingAreaDecoder: WorkingAreaDecoder = WorkingAreaDecoder): CityInfo {
        val city = City(code, name, country_code, workingAreaDecoder.decode(working_area))

        return CityInfo(city, language_code, currency)
    }
}