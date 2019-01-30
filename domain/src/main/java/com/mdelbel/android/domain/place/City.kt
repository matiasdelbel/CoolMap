package com.mdelbel.android.domain.place

open class City(
    private val code: String,
    private val name: String,
    private val countryCode: String,
    private val language: String,
    private val currency: String,
    private val workingArea: WorkingArea = WorkingArea()
) {

    fun code() = code
}