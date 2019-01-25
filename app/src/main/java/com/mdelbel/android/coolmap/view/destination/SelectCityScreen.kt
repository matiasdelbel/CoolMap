package com.mdelbel.android.coolmap.view.destination

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mdelbel.android.coolmap.R
import com.mdelbel.android.coolmap.data.permissions.MapPermissionsRequester
import com.mdelbel.android.coolmap.data.permissions.RequesterActivityBinder
import com.mdelbel.android.coolmap.view.destination.state.ItemViewModelFactory
import com.mdelbel.android.coolmap.view.destination.state.ViewState
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.CityDetail
import com.mdelbel.android.domain.place.Countries
import dagger.android.AndroidInjection
import javax.inject.Inject

class SelectCityScreen : AppCompatActivity(), DestinationView {

    @Inject
    lateinit var permissionRequester: MapPermissionsRequester
    private lateinit var requesterActivityBinder: RequesterActivityBinder

    private lateinit var listView: RecyclerView
    private lateinit var loading: ProgressBar
    private val viewAdapter: ItemAdapter = ItemAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SelectCityViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SelectCityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_main)
        listView = findViewById<RecyclerView>(R.id.screen_main_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = viewAdapter
        }
        loading = findViewById(R.id.screen_main_loading)

        attachRequesterToActivity()
        viewModel.screenState.observe(this, Observer<ViewState> { it.render(this@SelectCityScreen) })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.askPermissions()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) =
        permissionRequester.onRequestPermissionsResult(requestCode, grantResults)

    private fun attachRequesterToActivity() {
        requesterActivityBinder = RequesterActivityBinder(permissionRequester, this)
        lifecycle.addObserver(requesterActivityBinder)
    }

    override fun loading() {
        loading.visibility = View.VISIBLE
        listView.visibility = View.GONE
    }

    override fun goToMap(selected: CityDetail) {
        // TODO go to next screen
        Toast.makeText(this, "selected: ${selected.name()}", Toast.LENGTH_LONG).show()
    }

    override fun showCountries(countries: Countries) {
        loading.visibility = View.GONE
        listView.visibility = View.VISIBLE
        viewAdapter.submitList(ItemViewModelFactory().createFrom(countries))
    }

    override fun showCities(cities: Cities) {
        loading.visibility = View.GONE
        listView.visibility = View.VISIBLE
        viewAdapter.submitList(ItemViewModelFactory().createFrom(cities))
    }
}