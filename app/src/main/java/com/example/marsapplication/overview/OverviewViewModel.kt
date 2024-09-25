package com.example.marsapplication.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsapplication.network.MarsApi
import com.example.marsapplication.network.MarsProperty
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {


    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _property = MutableLiveData<MarsProperty>()
    val property : LiveData<MarsProperty>
        get() = _property


    init {
        getMarsRealEstateProperties()
    }


    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {

            try {
                var listResult = MarsApi.retrofitService.getProperties()
                if (listResult.size > 0){
                    _property.value = listResult[0]
                }
                _status.value = "Success: ${listResult.size} Mars properties retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }


}

