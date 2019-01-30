package com.mdelbel.android.coolmap.view.map.state

import android.content.Context
import com.mdelbel.android.coolmap.R
import com.mdelbel.android.coolmap.view.map.MapView

abstract class ErrorState(private val message: MessageError) : MapViewState {

    override fun render(view: MapView) {
        view.showError(message)
    }
}

object CityInformationNoFoundedErrorState : ErrorState(MessageError(R.string.map_error_country_information_not_found))

class MessageError(private val message: Int) {

    fun show(context: Context): String = context.resources.getString(message)
}