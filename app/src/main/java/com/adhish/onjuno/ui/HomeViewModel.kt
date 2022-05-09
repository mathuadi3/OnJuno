package com.adhish.onjuno.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhish.onjuno.data.HomeRepository
import com.adhish.onjuno.model.ResponseModel
import com.adhish.onjuno.model.YourCryptoHolding
import com.adhish.onjuno.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _emptyStateData = MutableLiveData<ResponseModel>()
    val emptyStateData: LiveData<ResponseModel> get() = _emptyStateData
    private val _valueStateData = MutableLiveData<ResponseModel>()
    val valueStateData: LiveData<ResponseModel> get() = _valueStateData
    private val _error = MutableLiveData<Result.Error>()
    val error: LiveData<Result.Error> get() = _error
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _holdingsList = arrayListOf<YourCryptoHolding>()
    val holdingsList get() = _holdingsList

    fun getEmptyStateData() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val response = withContext(Dispatchers.IO) { homeRepository.getEmptyStateData() }
            _isLoading.postValue(false)
            when (response) {
                is Result.Success -> _emptyStateData.postValue(response.result!!)
                is Result.Error -> _error.postValue(response)
            }
        }
    }

    fun getValueStateData() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val response = withContext(Dispatchers.IO) { homeRepository.getValueStateData() }
            _isLoading.postValue(false)
            when (response) {
                is Result.Success -> _valueStateData.postValue(response.result!!)
                is Result.Error -> _error.postValue(response)
            }
        }
    }

    fun addTransaction(cryptoHolding: YourCryptoHolding) {
        _holdingsList.add(0,cryptoHolding)
    }

}