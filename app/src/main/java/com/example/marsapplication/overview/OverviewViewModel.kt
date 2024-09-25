package com.example.marsapplication.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsapplication.network.MarsApi
import com.example.marsapplication.network.MarsProperty
import kotlinx.coroutines.launch

enum class MarsApiStatus {LOADING, ERROR, DONE}
class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties : LiveData<List<MarsProperty>>
        get() = _properties


    init {
        getMarsRealEstateProperties()
    }


    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            try {
                _status.value = MarsApiStatus.LOADING
                var listResult = MarsApi.retrofitService.getProperties()
                _status.value = MarsApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }


}

