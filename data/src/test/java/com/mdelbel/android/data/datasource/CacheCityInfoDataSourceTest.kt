package com.mdelbel.android.data.datasource

import com.mdelbel.android.domain.place.city.CityInfo
import com.mdelbel.android.domain.place.city.NoCityInfo
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class CacheCityInfoDataSourceTest {

    @Test
    fun `obtain by city code should returns value from cache`() {
        val city = mock(CityInfo::class.java)
        whenever(city.code()).thenReturn("ba")
        val dataSource = CacheCityInfoDataSource()
        dataSource.save(city)

        val result = dataSource.obtain("ba")

        assertEquals(city, result)
    }

    @Test
    fun `obtain by city code and not on cache should returns no city info`() {
        val cache = mutableMapOf<String, CityInfo>()
        val city = mock(CityInfo::class.java)
        cache["ba"] = city
        val dataSource = CacheCityInfoDataSource(cache)

        val result = dataSource.obtain("other")

        assertEquals(NoCityInfo, result)
    }

}