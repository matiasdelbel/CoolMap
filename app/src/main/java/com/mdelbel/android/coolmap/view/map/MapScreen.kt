package com.mdelbel.android.coolmap.view.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
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
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.mdelbel.android.coolmap.R
import com.mdelbel.android.coolmap.view.map.state.MapViewState
import com.mdelbel.android.coolmap.view.map.state.MessageError
import com.mdelbel.android.domain.location.Location
import com.mdelbel.android.domain.location.LocationOnCountry
import com.mdelbel.android.domain.place.Cities
import com.mdelbel.android.domain.place.Country
import com.mdelbel.android.domain.place.city.City
import com.mdelbel.android.domain.place.city.CityInfo
import dagger.android.AndroidInjection
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

//TODO rotar pincha
class MapScreen : AppCompatActivity(), OnMapReadyCallback, MapView {

    companion object {

        fun start(context: Context, cityDetail: City) {
            val intent = Intent(context, MapScreen::class.java)
            intent.putExtra(EXTRA_MAP_INPUT, MapScreenInput(cityDetail.code(), cityDetail.countryCode()))
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

        map.setOnCameraIdleListener {
            val cameraPosition = map.cameraPosition

            val center = Location(
                cameraPosition.target.latitude,
                cameraPosition.target.longitude
            )
            viewModel.onNewCenter(center)

            viewModel.onNewZoomLevel(ZoomLevel(cameraPosition.zoom))
        }

        val extra = intent.getParcelableExtra<MapScreenInput>(EXTRA_MAP_INPUT)

        map.setOnMarkerClickListener {
            viewModel.obtainCitiesFor(
                LocationOnCountry(
                    Location(it.position.latitude, it.position.longitude),
                    Country(code = extra.countryCode)
                )
            )
            true
        }


        viewModel.obtainGeolocationCityInformation(extra.selectedCityCode)
        viewModel.obtainCitiesFor(Country(code = extra.countryCode))
    }

    override fun showCityInformation(city: CityInfo) {
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

    override fun showWorkingAreas(areas: List<List<LatLng>>) {
        map.clear()
        for (area in areas) {
            map.addPolygon(
                PolygonOptions()
                    .addAll(area)
                    .fillColor(ContextCompat.getColor(this, R.color.colorPrimaryLight))
                    .strokeWidth(0.0f)
            )
        }
    }

    override fun showCities(cities: Cities) {
        map.clear()
        for (city in cities.asCityDetailsList()) {
            map.addMarker(MarkerOptions().position(city.center().asLatLng()))
        }
    }


    override fun moveTo(locations: List<LatLng>) {
        val zoom = 10
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

@Parcelize
data class MapScreenInput(val selectedCityCode: String, val countryCode: String) : Parcelable

private const val EXTRA_MAP_INPUT = "EXTRA_MAP_INPUT"