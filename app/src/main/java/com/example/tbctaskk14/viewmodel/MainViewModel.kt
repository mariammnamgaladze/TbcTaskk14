package com.example.tbctaskk14.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbctaskk14.ResponseCatcher
import com.example.tbctaskk14.model.Data
import com.example.tbctaskk14.network.RetroObj
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _myFlow = MutableStateFlow<ResponseCatcher>(ResponseCatcher.InProcess())
    val myFlow: StateFlow<ResponseCatcher> get() = _myFlow


    fun info() {
        viewModelScope.launch {
            val response = RetroObj.getData().info()
            val body: Data? = response.body()
            if (response.isSuccessful && body != null) {
                _myFlow.emit(ResponseCatcher.Success(response.body()?.content ?: emptyList()))
            }else{
                _myFlow.emit(ResponseCatcher.Error(response.errorBody().toString()))
            }
        }
    }
}