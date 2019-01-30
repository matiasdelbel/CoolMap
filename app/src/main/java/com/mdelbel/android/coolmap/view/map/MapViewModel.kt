package com.mdelbel.android.coolmap.view.map

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mdelbel.android.usecases.place.ObtainCityDetail
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MapViewModel @Inject constructor(private val obtainCityDetailUseCase: ObtainCityDetail) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun obtainCityInformation(cityCode: String) {
        compositeDisposable.add(
            obtainCityDetailUseCase(cityCode)
                .subscribe({
                    Log.e("", "")
                    //TODO
                }, {
                    Log.e("", "")
                    //TODO
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}