package com.mdelbel.android.coolmap.view.destination

import com.mdelbel.android.domain.place.city.Cities
import com.mdelbel.android.domain.place.city.City
import com.mdelbel.android.domain.place.Countries
import com.mdelbel.android.domain.place.Country

class ItemViewModelFactory {

    fun createFrom(countries: Countries, selectionAction: (item: ItemViewModel) -> Unit): List<ItemViewModel> {
        val items = mutableListOf<ItemViewModel>()
        countries.asCountryCollection().forEach {
            items.add(ItemViewModel(it.code(), it.name(), it, selectionAction))
        }

        return items
    }

    fun createFrom(cities: Cities, selectionAction: (item: ItemViewModel) -> Unit): List<ItemViewModel> {
        val items = mutableListOf<ItemViewModel>()
        cities.asCityCollection().forEach {
            items.add(ItemViewModel(it.code(), it.name(), it, selectionAction))
        }
        return items
    }

    fun extractCountry(item: ItemViewModel): Country = item.payload as Country

    fun extractCity(item: ItemViewModel): City = item.payload as City
}