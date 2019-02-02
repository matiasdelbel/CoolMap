package com.mdelbel.android.coolmap.view.destination

import com.mdelbel.android.domain.place.Countries
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.city.Cities
import com.mdelbel.android.domain.place.city.City
import org.junit.Assert.assertEquals
import org.junit.Test

class ItemViewModelFactoryTest {

    @Test
    fun `create from countries should generate a list of view models`() {
        val argentina = Country("AR", "Argentina")
        val brazil = Country("BR", "Brazil")
        val countries = Countries(listOf(argentina, brazil))

        val items = ItemViewModelFactory().createFrom(countries) {}

        assertEquals(2, items.size)

        assertEquals("AR", items[0].code)
        assertEquals("Argentina", items[0].title)

        assertEquals("BR", items[1].code)
        assertEquals("Brazil", items[1].title)
    }

    @Test
    fun `create from cities should generate a list of view models`() {
        val buenosAires = City("BA", "Buenos Aires", "AR")
        val cordoba = City("CBA", "Cordoba", "AR")
        val cities = Cities(listOf(buenosAires, cordoba))

        val items = ItemViewModelFactory().createFrom(cities) {}

        assertEquals(2, items.size)

        assertEquals("BA", items[0].code)
        assertEquals("Buenos Aires", items[0].title)

        assertEquals("CBA", items[1].code)
        assertEquals("Cordoba", items[1].title)
    }
}