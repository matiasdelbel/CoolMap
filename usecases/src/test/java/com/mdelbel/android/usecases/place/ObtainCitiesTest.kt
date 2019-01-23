package com.mdelbel.android.usecases.place

import com.mdelbel.android.data.repository.CityRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.mock

class ObtainCitiesTest {

    @Test
    fun `invoke should call obtain cities from repository`() {
        val repository = mock(CityRepository::class.java)
        val useCase = ObtainCities(repository)

        useCase()

        verify(repository).obtainAll()
    }
}