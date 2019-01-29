package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.location.UserLocation
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.Country
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class MemoryCityDataSourceTest {

    @Test
    fun `obtain all should returns cache`() {
        val cache = mock(Cities::class.java)
        val dataSource = MemoryCityDataSource(cache)

        val all = dataSource.obtainAll()

        assertEquals(cache, all)
    }

    @Test
    fun `save should override cache`() {
        val cache = mock(Cities::class.java)
        val dataSource = MemoryCityDataSource()

        dataSource.save(cache)

        assertEquals(cache, dataSource.obtainAll())
    }

    @Test
    fun `obtain by location should call cities pick city on`() {
        val cache = mock(Cities::class.java)
        val location = mock(UserLocation::class.java)
        val dataSource = MemoryCityDataSource(cache)

        dataSource.obtainBy(location)

        verify(cache).pickCityOn(location)
    }

    @Test
    fun `obtain by country should call cities obtain by country`() {
        val cache = mock(Cities::class.java)
        val country = mock(Country::class.java)
        val dataSource = MemoryCityDataSource(cache)

        dataSource.obtainBy(country)

        verify(cache).obtainBy(country)
    }
}