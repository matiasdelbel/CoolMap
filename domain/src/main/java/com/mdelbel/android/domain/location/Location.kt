package com.mdelbel.android.domain.location

open class Location(
    private val latitude: Double,
    private val longitude: Double,
    private val city: String  = "",
    private val country: String = ""
)

//TODO dividir en UserLocation y Location
//TODO lo estoy compartiendo entre el request y el buscar lugar encima no uso todos los atributos