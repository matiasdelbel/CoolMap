package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.datasource.CountryDataSource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class ObtainCountriesTest {

    @Test
    fun `invoke should call obtain countries from data source`() {
        val dataSource = mock(CountryDataSource::class.java)
        val useCase = ObtainCountries(dataSource)

        useCase()

        verify(dataSource).obtainAll()
    }
}