package com.mdelbel.android.coolmap.view.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolygonOptions
import com.mdelbel.android.coolmap.R
import com.mdelbel.android.coolmap.view.map.state.MapViewState
import com.mdelbel.android.coolmap.view.map.state.MessageError
import com.mdelbel.android.domain.place.Area
import com.mdelbel.android.domain.place.City
import com.mdelbel.android.domain.place.CityDetail
import dagger.android.AndroidInjection
import javax.inject.Inject


class MapScreen : AppCompatActivity(), OnMapReadyCallback, MapView {

    companion object {

        fun start(context: Context, cityDetail: CityDetail) {
            val intent = Intent(context, MapScreen::class.java)
            intent.putExtra(EXTRA_CITY_DETAIL, cityDetail.code())
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MapViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MapViewModel::class.java)
    }

    private lateinit var map: GoogleMap
    private lateinit var nameView: TextView
    private lateinit var countryView: TextView
    private lateinit var currencyView: TextView
    private lateinit var languageView: TextView
    private lateinit var errorView: TextView
    private lateinit var loadingView: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.screen_map_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        nameView = findViewById(R.id.panel_city_information_city_name)
        countryView = findViewById(R.id.panel_city_information_country_code)
        currencyView = findViewById(R.id.panel_city_information_currency)
        languageView = findViewById(R.id.panel_city_information_language)
        errorView = findViewById(R.id.panel_city_information_error)
        loadingView = findViewById(R.id.panel_city_information_progress_bar)

        viewModel.screenState.observe(this, Observer<MapViewState> { it.render(this@MapScreen) })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        viewModel.obtainGeolocationCityInformation(intent.getStringExtra(EXTRA_CITY_DETAIL))
    }

    override fun showCityInformation(city: City) {
        nameView.visibility = View.VISIBLE
        nameView.text = city.name()
        countryView.visibility = View.VISIBLE
        countryView.text = city.countryCode()
        currencyView.visibility = View.VISIBLE
        currencyView.text = city.currency()
        languageView.visibility = View.VISIBLE
        languageView.text = city.language()

        errorView.visibility = View.GONE
        loadingView.visibility = View.GONE
    }

    override fun showWorkingAreas(areas: List<Area>) {
        for (area in areas) {
            map.addPolygon(
                PolygonOptions()
                    .addAll(area.asLatLngPoints())
                    .fillColor(ContextCompat.getColor(this, R.color.colorPrimaryLight))
                    .strokeWidth(0.0f)
            )
        }
    }


    override fun moveTo(locations: List<LatLng>) {
        val zoom = 50
        var latLngBoundsBuilder = LatLngBounds.Builder()
        for (location in locations) {
            latLngBoundsBuilder = latLngBoundsBuilder.include(location)
        }

        map.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBoundsBuilder.build(), zoom))
    }

    override fun loading() {
        nameView.visibility = View.GONE
        countryView.visibility = View.GONE
        currencyView.visibility = View.GONE
        languageView.visibility = View.GONE
        errorView.visibility = View.GONE

        loadingView.visibility = View.VISIBLE
    }

    override fun showError(error: MessageError) {
        nameView.visibility = View.GONE
        countryView.visibility = View.GONE
        currencyView.visibility = View.GONE
        languageView.visibility = View.GONE
        loadingView.visibility = View.GONE

        errorView.visibility = View.VISIBLE
        errorView.text = error.show(this)
    }
}

private const val EXTRA_CITY_DETAIL = "EXTRA_CITY_DETAIL"