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
import com.mdelbel.android.domain.place.Countries
import com.mdelbel.android.domain.place.city.Cities
import com.mdelbel.android.domain.place.city.City
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

        initViews()
        attachRequesterToActivity()
        observeViewModelState()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.askPermissions()
        }
    }

    override fun onBackPressed() = viewModel.restoreToStateBefore()

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) =
        permissionRequester.onRequestPermissionsResult(requestCode, grantResults)

    private fun initViews() {
        listView = findViewById<RecyclerView>(R.id.screen_main_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = viewAdapter
        }
        loadingView = findViewById(R.id.screen_main_loading)
        errorView = findViewById(R.id.screen_main_error)
    }

    private fun attachRequesterToActivity() {
        requesterActivityBinder = RequesterActivityBinder(permissionRequester, this)
        lifecycle.addObserver(requesterActivityBinder)
    }

    private fun observeViewModelState() =
        viewModel.screenState.observe(this, Observer<DestinationViewState> { it.render(this@SelectDestinationScreen) })

    override fun loading() {
        changeVisibility(View.GONE, listView, errorView)
        changeVisibility(View.VISIBLE, loadingView)
    }

    override fun showCountries(countries: Countries) {
        val factory = ItemViewModelFactory()
        showList(factory.createFrom(countries) { viewModel.countrySelected(factory.extractCountry(it)) })
    }

    override fun showCities(cities: Cities) {
        val factory = ItemViewModelFactory()
        showList(factory.createFrom(cities) { viewModel.citySelected(factory.extractCity(it)) })
    }

    override fun goToMap(selected: City) {
        MapScreen.start(this, selected)
        exit()
    }

    override fun showError(message: MessageError) {
        changeVisibility(View.GONE, loadingView, listView)
        changeVisibility(View.VISIBLE, errorView)

        errorView.text = message.show(this)
    }

    override fun exit() = finish()

    private fun showList(itemModels: List<ItemViewModel>) {
        changeVisibility(View.GONE, loadingView, errorView)
        changeVisibility(View.VISIBLE, listView)

        viewAdapter.submitList(itemModels)
    }

    private fun changeVisibility(visibility: Int, vararg views: View) {
        views.forEach { view -> view.visibility = visibility }
    }
}