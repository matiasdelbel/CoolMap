package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.Countries
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class MemoryCountryDataSourceTest {

    @Test
    fun `obtain all should returns cache`() {
        val cache = mock(Countries::class.java)
        val dataSource = CacheCountriesDataSource(cache)

        val all = dataSource.obtainAll()

        assertEquals(cache, all)
    }

    @Test
    fun `save should override cache`() {
        val cache = mock(Countries::class.java)
        val dataSource = CacheCountriesDataSource()

        dataSource.save(cache)

        assertEquals(cache, dataSource.obtainAll())
    }
}