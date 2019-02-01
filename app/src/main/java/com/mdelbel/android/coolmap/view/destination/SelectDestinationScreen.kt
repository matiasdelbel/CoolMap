package com.mdelbel.android.coolmap.view.destination

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mdelbel.android.coolmap.R
import com.mdelbel.android.coolmap.data.permissions.MapPermissionsRequester
import com.mdelbel.android.coolmap.data.permissions.RequesterActivityBinder
import com.mdelbel.android.coolmap.view.destination.state.DestinationViewState
import com.mdelbel.android.coolmap.view.destination.state.MessageError
import com.mdelbel.android.coolmap.view.map.MapScreen
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.City
import com.mdelbel.android.domain.place.Countries
import dagger.android.AndroidInjection
import javax.inject.Inject

class SelectDestinationScreen : AppCompatActivity(), SelectDestinationView {

    @Inject
    lateinit var permissionRequester: MapPermissionsRequester
    private lateinit var requesterActivityBinder: RequesterActivityBinder

    private lateinit var listView: RecyclerView
    private lateinit var loadingView: ProgressBar
    private lateinit var errorView: TextView
    private val viewAdapter: ItemAdapter = ItemAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SelectDestinationViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SelectDestinationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_destination)
        listView = findViewById<RecyclerView>(R.id.screen_main_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = viewAdapter
        }
        loadingView = findViewById(R.id.screen_main_loading)
        errorView = findViewById(R.id.screen_main_error)

        attachRequesterToActivity()
        viewModel.screenState.observe(this, Observer<DestinationViewState> { it.render(this@SelectDestinationScreen) })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.askPermissions()
        }
    }

    override fun onBackPressed() {
        viewModel.returnsStateBefore()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) =
        permissionRequester.onRequestPermissionsResult(requestCode, grantResults)

    private fun attachRequesterToActivity() {
        requesterActivityBinder = RequesterActivityBinder(permissionRequester, this)
        lifecycle.addObserver(requesterActivityBinder)
    }

    override fun loading() {
        loadingView.visibility = View.VISIBLE
        errorView.visibility = View.GONE
        listView.visibility = View.GONE
    }

    override fun goToMap(selected: City) {
        finish()
        MapScreen.start(this, selected)
    }

    override fun showCountries(countries: Countries) {
        val factory = ItemViewModelFactory()
        showList(factory.createFrom(countries) { viewModel.countrySelected(factory.extractCountry(it)) })
    }

    override fun showCities(cities: Cities) {
        val factory = ItemViewModelFactory()
        showList(factory.createFrom(cities) { viewModel.citySelected(factory.extractCity(it)) })
    }

    override fun showError(message: MessageError) {
        errorView.visibility = View.VISIBLE
        errorView.text = message.show(this)

        loadingView.visibility = View.GONE
        listView.visibility = View.GONE
    }

    override fun exit() = finish()

    private fun showList(itemModels: List<ItemViewModel>) {
        loadingView.visibility = View.GONE
        errorView.visibility = View.GONE

        listView.visibility = View.VISIBLE
        viewAdapter.submitList(itemModels)
    }
}