package com.mdelbel.android.coolmap.view.destination.state

import android.content.Context
import com.mdelbel.android.coolmap.R
import com.mdelbel.android.coolmap.view.destination.SelectDestinationView

abstract class ErrorState(private val message: MessageError) : DestinationViewState {

    override fun render(view: SelectDestinationView) {
        view.showError(message)
    }
}

object NoCitiesFoundedErrorState : ErrorState(MessageError(R.string.select_destination_error_cities_not_found))

object NoCountriesFoundedErrorState : ErrorState(MessageError(R.string.select_destination_error_countries_not_found))

class MessageError(private val message: Int) {

    fun show(context: Context): String = context.resources.getString(message)
}