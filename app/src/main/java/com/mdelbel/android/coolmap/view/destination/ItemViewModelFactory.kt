package com.mdelbel.android.coolmap.view.destination.state

import com.mdelbel.android.coolmap.view.destination.ItemViewModel
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.Countries

class ItemViewModelFactory {

    fun createFrom(countries: Countries): List<ItemViewModel> {
        val items = mutableListOf<ItemViewModel>()
        countries.asCountryList().forEach {
            items.add(ItemViewModel(it.code(), it.name()))
        }

        return items
    }

    fun createFrom(cities: Cities): List<ItemViewModel> {
        val items = mutableListOf<ItemViewModel>()
        cities.asCityDetailsList().forEach {
            items.add(ItemViewModel(it.code(), it.name()))
        }
        return items
    }
}