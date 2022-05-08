package com.adhish.onjuno.ui.empty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhish.onjuno.data.HomeRepository
import com.adhish.onjuno.model.ResponseModel
import com.adhish.onjuno.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class EmptyStateViewModel @Inject constructor(
    val homeRepository: HomeRepository
) : ViewModel() {

    private val _emptyStateData = MutableLiveData<ResponseBody>()
    val emptyStateData : LiveData<ResponseBody> get() = _emptyStateData
    private val _valueStateData = MutableLiveData<ResponseModel>()
    val valueStateData : LiveData<ResponseModel> get() = _valueStateData

    fun getEmptyStateData(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){homeRepository.getEmptyStateData()}
            when(response){
                is Result.Success-> _emptyStateData.postValue(response.result!!)
            }
        }
    }

}