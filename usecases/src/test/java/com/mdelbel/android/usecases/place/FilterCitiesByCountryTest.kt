package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.repository.CityRepository
import com.mdelbel.android.domain.place.Country
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class FilterCitiesByCountryTest {

    @Test
    fun `invoke should call obtain cities by country from repository`() {
        val repository = mock(CityRepository::class.java)
        val country = mock(Country::class.java)
        val useCase = FilterCitiesByCountry(repository)

        useCase(country)

        verify(repository).obtainBy(country)
    }
}