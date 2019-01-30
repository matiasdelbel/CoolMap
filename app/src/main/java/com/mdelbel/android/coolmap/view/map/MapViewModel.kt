package com.mdelbel.android.coolmap.view.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdelbel.android.coolmap.view.map.state.CityInformationNoFoundedErrorState
import com.mdelbel.android.coolmap.view.map.state.DisplayCityInformationState
import com.mdelbel.android.coolmap.view.map.state.LoadingState
import com.mdelbel.android.coolmap.view.map.state.MapViewState
import com.mdelbel.android.usecases.place.ObtainCityDetail
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MapViewModel @Inject constructor(private val obtainCityDetailUseCase: ObtainCityDetail) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    internal val screenState = MutableLiveData<MapViewState>().apply { setValue(LoadingState()) }

    fun obtainCityInformation(cityCode: String) {
        compositeDisposable.add(
            obtainCityDetailUseCase(cityCode)
                .subscribe({
                    screenState.postValue(DisplayCityInformationState(it))
                }, {
                    screenState.postValue(CityInformationNoFoundedErrorState)
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}